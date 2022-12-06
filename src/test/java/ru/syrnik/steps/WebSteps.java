package ru.syrnik.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открываем сайт {url}")
    public void openStartPage(String url) {
        open(url);
    }

    @Step("Ищем репозитрий {repo}")
    public void searchRepo(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
    }

    @Step("Открываем репозиторий {repo}")
    public void openRepo(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Переходим на вкладку Issues")
    public void openIssueTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с наименованием {issue}")
    public void checkIssueByName(String issue) {
        $(withText(issue)).should(Condition.exist);
    }
}
