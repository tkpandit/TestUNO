package tkptest.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UnoQA {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://uno-qa.rategain.com/");

		driver.findElement(By.xpath("//input[@formcontrolname='username']")).sendKeys("tapan.pandit@rategain.com");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("RG@2023uno");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".loader")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".loader"))));

		List<WebElement> tabButtons = driver.findElements(By.xpath("//div[@role='tab']"));
		WebElement tabs = tabButtons.stream().filter(tabBtn -> tabBtn
				.findElement(By.cssSelector(".mat-tab-label-content")).getText().equals("Property List")).findFirst()
				.orElse(null);
		tabs.click();

		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".tablebox")));

		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='transparentScrollbarDiv']/tbody/tr"));
		List<WebElement> matchingRows = rows.stream().filter(row -> row.getText().contains("BASE OCCU#2 ")).toList();

		matchingRows.forEach(row -> {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			cells.forEach(cell -> {
				System.out.print(cell.getText() + "\t");
			});
			WebElement viewButton = row.findElement(By.xpath(".//button[text()='View']"));
			viewButton.click();
		});

		String dashHeading = driver.findElement(By.cssSelector(".pageNameHeading")).getText();
		Assert.assertTrue(dashHeading.equalsIgnoreCase("DASHBARD"));

	}
}
