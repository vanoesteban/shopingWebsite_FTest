package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Browser;

public class SearchPO <M extends WebElement> extends BasePO<M>{


    /**
     * Constructor - PageFactory
     *
     * @throws Exception
     */
    public SearchPO() throws Exception {
        super();
    }

    @FindBy (id ="search_query_top")
    protected M searchQueryTop;

    @FindBy (className = "alert-warning")
    protected M notFound;

    //setters and getters
    public M getNotFound(){
        return this.notFound;
    }

    public M getSearchQueryTop(){
        return this.searchQueryTop;
    }

    public void searchQueryTop(String query){
        Browser.write(this.searchQueryTop, query);
        Browser.submit(this.searchQueryTop);
    }


}
