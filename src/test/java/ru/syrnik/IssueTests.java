package ru.syrnik;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import ru.syrnik.steps.WebSteps;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class IssueTests {

    static final String URL = "https://github.com";
    static final String REPOSITORY = "syrnik-by/qa_guru_hw06";
    static final String ISSUE = "TestAllure";

    @Test
    public void checkIssueTestWithSelenideListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(URL);

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();

        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUE)).should(Condition.exist);
    }

    @Test
    public void checkIssueTestWithLambda() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем сайт GitHub", ()->{
            open(URL);
        });
        step("Ищем репозитрий " + REPOSITORY, ()->{
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Открываем репозиторий " + REPOSITORY, ()->{
            $(linkText(REPOSITORY)).click();
        });
        step("Переходим на вкладку Issues", ()->{
            $("#issues-tab").click();
        });
        step("Проверяем наличие issue с наименованием " + ISSUE, ()->{
            $(withText(ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void checkIssueTestWithSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps webSteps = new WebSteps();

        webSteps.openStartPage(URL);
        webSteps.searchRepo(REPOSITORY);
        webSteps.openRepo(REPOSITORY);
        webSteps.openIssueTab();
        webSteps.checkIssueByName(ISSUE);

    }
}
