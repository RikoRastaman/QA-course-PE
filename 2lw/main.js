const jsdom = require("jsdom");
const {JSDOM} = jsdom;
const fs = require('fs');

const request = require("request-promise-native");

var STREAM = fs.createWriteStream("./test.txt");
const MAIN_URL = "http://52.136.215.164/";
const URL = `${MAIN_URL}/broken-links`;

let errors = 0;
let ok = 0;

const processed = new Set();

async function main() {
    console.log("Testing  Start..");
    await testLink(URL + "/");

    STREAM.write(`\n${ok}\n${ Date().toString()}` + '\n');
    STREAM.write(`\n${errors}\n${ Date().toString()}` + '\n');

    console.log("Testing Done!");
}

async function testLink(url) {
    const properties = {
        encoding: "utf8",
        timeout: 5000,
        resolveWithFullResponse: true,
    };
    try {
        const response = await request(url, properties);
        ok++;
        STREAM.write(`${response.statusCode}\t${url}` + '\n');
        await testChildLink(response.body);
    } catch (error) {
        errors++;
        STREAM.write(`${error.statusCode}\t${url}` + '\n');
    }
}

async function testChildLink(content) {
    const {window} = new JSDOM(content);
    const {document} = window;
    const nodes = document.getElementsByTagName("a");
    const links = getChildLinks(nodes);
    for (const link of links) {
        const url = `${URL}/${link}`;
        if (checkProcessed(url) || checkProcessed(link)) {
            continue;
        }
        makeProcessed(link);
        makeProcessed(url);
        if (link.startsWith("http")) {
            if (isDifferentLink(link)) {
                continue;
            }
            await testLink(link);
        } else if (url.startsWith("http")) {
            await testLink(url);
        }
    }
}

function getChildLinks(nodes) {
    return Array.from(nodes)
        .map(node => node.href.trim())
        .filter(Boolean);
}

function checkProcessed(link) {
    return processed.has(link);
}

function makeProcessed(url) {
    processed.add(url);
}

function isDifferentLink(url) {
    return !url.startsWith(MAIN_URL);
}

main();
