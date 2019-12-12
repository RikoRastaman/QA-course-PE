package testsPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodInDto {

    private String id;
    private String category_id;
    private String title;
    private String alias;
    private String content;
    private String price;
    private String old_price;
    private String status;
    private String keywords;
    private String description;
    private String hit;
}

