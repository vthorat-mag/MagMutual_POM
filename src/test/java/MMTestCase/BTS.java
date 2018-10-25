package MMTestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mm.browsers.BrowserTypes;
import com.mm.dto.FinancePageDTO;
import com.mm.dto.HomePageDTO;
import com.mm.dto.LoginPageDTO;
import com.mm.dto.PolicyIndicationPageDTO;
import com.mm.dto.PolicyQuotePageDTO;
import com.mm.pages.FinancePage;
import com.mm.pages.HomePage;
import com.mm.pages.LoginPage;
import com.mm.pages.PolicyBinderPage;
import com.mm.pages.PolicyIndicationPage;
import com.mm.pages.PolicyQuotePage;
import com.mm.pages.RateApolicyPage;
import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.mm.utils.IntegrateRallyRestAPI;
import com.mm.utils.TestCaseDetails;
import com.relevantcodes.extentreports.LogStatus;

public class BTS extends ExtentReporter {

    // Global Assignment/initialization of variables.
    IntegrateRallyRestAPI iR;
    public static WebDriver driver;
    public static HashMap<String, List<String>> testDataMap;

    String serverURL = "";
    String username = "";
    String password = "";
    String workspace = "";
    String project = "";
    String testcaseFormattedID = ""; // method.getName();
    String buildNumber = "";
    String notes = "";
    double duration = 0.0; // get from extent test
    String verdict = "Pass";
    String userRef = "";
    String ExcelPath = System.getProperty("user.dir") + "\\src\\main\\resources\\Form_Data.xlsx";

    @BeforeClass(alwaysRun = true)
    public void beforeClass() throws IOException, URISyntaxException {
        Properties configProperties = new Properties();
        InputStream inputFile = new FileInputStream("config.properties");
        configProperties.load(inputFile);

        // Temporary variables declared, this would be fetched from properties
        // file.
        serverURL = configProperties.getProperty("ServerURL");
        username = configProperties.getProperty("Username");
        password = configProperties.getProperty("Password");
        workspace = configProperties.getProperty("Workspace");
        project = configProperties.getProperty("Project");
        buildNumber = configProperties.getProperty("BuildNumber");
        notes = configProperties.getProperty("DefaultNote");
        duration = 0.0;

        // get from extent test "Blocked", "Deferred", "Enhancement", "Error",
        // "Fail",
        // "Hold", "In Progress", "Inconclusive", "Invalid", "Out of Scope",
        // "Pass",
        // "Waiting for Policy" verdict = "Pass";

        iR = new IntegrateRallyRestAPI();
        // password = iR.decodeString(password);
        // This should go in Before Suite method
        userRef = iR.getRallyUserDetails(serverURL, username, password);
        createReportFolder(this.getClass().getSimpleName());
    }

    // Extent report initialization before every test case.
    @BeforeMethod(alwaysRun = true)
    public void Setup(Method method) throws Exception {

        Process processkillpdf = Runtime.getRuntime()
                .exec("TASKKILL /F /FI \"USERNAME eq " + System.getProperty("user.name") + "\" /IM savePdf.exe");
        Process processkillIE = Runtime.getRuntime()
                .exec("TASKKILL /F /FI \"USERNAME eq " + System.getProperty("user.name") + "\" /IM iexplore.exe");
        Process processkillSaveExcel = Runtime.getRuntime()
                .exec("TASKKILL /F /FI \"USERNAME eq " + System.getProperty("user.name") + "\" /IM saveExcel.exe");
        Process processkillCloseExcel = Runtime.getRuntime()
                .exec("TASKKILL /F /FI \"USERNAME eq " + System.getProperty("user.name") + "\" /IM Excel.exe");
        Process processkillImageRight = Runtime.getRuntime().exec(
                "TASKKILL /F /FI \"USERNAME eq " + System.getProperty("user.name") + "\" /IM imageright.desktop.exe");

        // This code will create file storage folders if not exists already.
        verifyFolderExistOrNot("TempsaveExcel");
        verifyFolderExistOrNot("savePDF");

        // This code is to clean saveExcel folder
        File folder = new File("C:\\TempsaveExcel");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                listOfFiles[i].delete();
            }
        }
        // verifyPDF deletion
        File genericVerifyPDFFile = new File("C:\\savePDF\\verifyPDF.pdf");
        genericVerifyPDFFile.delete();
        driver = BrowserTypes.getDriver();

        ExtentReporter.logger = ExtentReporter.report.startTest(method.getName(),
                method.getAnnotation(Test.class).description());
        ExtentReporter.excelPath = "";
        testcaseFormattedID = method.getName();
        // Code to populate HashMap from excel
        // Instantiate ExcelUtil and call testData and fill a HashMap
        // testDataMap
        TestCaseDetails.testcaseId = method.getName().toUpperCase();
        ExcelUtil excelUtil = new ExcelUtil();
        try {
            TestCaseDetails.testDataDictionary = excelUtil.testData(TestCaseDetails.testcaseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * @Test(description =
     * "Rate a policy that existed before the change or deployment to confirm it still displays as expected"
     * , groups = { "BTS Smoke Test" }, priority = 0) public void TC42239()
     * throws Exception { LoginPageDTO lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); LoginPage loginpage =
     * new LoginPage(driver); RateApolicyPage rateapolicyPage = new
     * RateApolicyPage(driver); loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToPolicyPage(); HomePage homepage = new
     * HomePage(driver); String policyNum =
     * homepage.policySearchUsingSearchCriteria();
     * rateapolicyPage.rateFunctionality(policyNum); }
     * 
     * @Test(description =
     * "This test case will cover smoke test for Hospital(BTS) CIS\r\n" +
     * "Verify CIS opens \r\n" + "Search an entity/person\r\n" +
     * "Navigate through the CIS screens", groups = { "BTS Smoke Test" },
     * priority = 1) public void TC42253() throws Exception { LoginPageDTO lpDTO
     * = new LoginPageDTO(TestCaseDetails.testDataDictionary); LoginPage
     * loginpage = new LoginPage(driver);
     * loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToCISPage().searchAndSelectAClientName()
     * .verifyPagesHavingMenuOnPersonPageAreDisplayed().
     * verifyPagesWithoutSubMenu(); }
     * 
     * @Test(description = "Verify Add Organization", groups = {
     * "BTS Smoke Test" }, priority = 2) public void TC42404() throws Exception
     * { LoginPage loginpage = new LoginPage(driver); LoginPageDTO lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); ExcelUtil exlUtil = new
     * ExcelUtil(); loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToCISPage().navigateToAddOrgPage(); CISPage
     * cisPage = new CISPage(driver); String OrganizationName =
     * cisPage.addOrganizationInformation();
     * cisPage.addOrgAddress().selectZipCode().addPhoneNumber().
     * searchRecentlyAddedOrganisation(OrganizationName);
     * exlUtil.writeData("TC42249", "lastOrgName", OrganizationName, 1,
     * ExcelPath); }
     */
    @Test(description = "QA Hospital Indication", groups = { "BTS Smoke Test" }, priority = 3)
    public void TC42249() throws Exception {
        String Empty = "";
        LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
        LoginPage loginpage = new LoginPage(driver);
        HomePage homepage = new HomePage(driver);
        HomePageDTO homePageDTO = new HomePageDTO(TestCaseDetails.testDataDictionary);
        ExcelUtil exlUtil = new ExcelUtil();
        PolicyIndicationPage policyindicationpage = new PolicyIndicationPage(driver);
        PolicyIndicationPageDTO indicationPageDTO = new PolicyIndicationPageDTO(TestCaseDetails.testDataDictionary);
        PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);
        loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage().create_New();
        String ParentWindow = homepage.create_Quote();
        homepage.searchEntity(homePageDTO.lastOrgName, Empty).selectEntity(ParentWindow, Empty).selectPolicyTypeForQA()
                .updatePolicyDetails();
        String policyNo = policyindicationpage.open_Underwriter();
        policyindicationpage.add_UnderwriterForQA(policyNo).closeUnderwriter().addAgent().selectRiskTab()
                .addRiskInformation(indicationPageDTO.riskCounty.get(0), indicationPageDTO.riskSpeciality.get(0),
                        indicationPageDTO.riskTypeValue.get(0), Empty)
                .selectCoverageTab().selectAddCoverageButton()
                .selectCoverageFromPopupListAddDatePremium(indicationPageDTO.retroDate.get(0)).closeAddCoverageWindow()
                .selectCoverageFromGridList().addCoverageClass();
        Thread.sleep(10000);
        String PolicyNo = policyindicationpage.policyNo();
        policyindicationpage.coverageUpdates(PolicyNo).openLimitSharingTab(PolicyNo).addSharedGroup(PolicyNo)
                .closeLimitSharingtab().rateFunctionality(PolicyNo);
        policyQuotePage.clickPreviewTab(PolicyNo).savePDF(testcaseFormattedID).verifyPdfContent();
        policyQuotePage.saveOptionOfficial(PolicyNo);
        exlUtil.writeData("TC42238", "PolicyNum", PolicyNo, 1, ExcelPath);
    }

    @Test(description = "QA Hospital Quote", groups = { "BTS Smoke Test" }, priority = 4)
    public void TC42238() throws Exception {
        LoginPageDTO lpDTO;
        LoginPage loginpage;
        RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
        PolicyQuotePage policyquotepage = new PolicyQuotePage(driver);
        ExcelUtil exlUtil = new ExcelUtil();
        PolicyQuotePageDTO policyquotepagedto = new PolicyQuotePageDTO(TestCaseDetails.testDataDictionary);
        lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
        loginpage = new LoginPage(driver);
        loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
                .searchPolicyPolicyQuotePageWithCopyTOQuteBTS_QA().copyFromActionDropDownForQA()
                .changePolicyPhase(policyquotepagedto.quotePhaseValue).coverageDetailsSelect();
        String policyNumber = policyquotepage.policyNo();
        rateapolicyPage.coverageUpdates(policyNumber);
        exlUtil.writeData("TC42242", "PolicyNum", policyNumber, 1, ExcelPath);
        policyquotepage.rateFunctionalityWithoutPremiumVerification(policyNumber).clickPreviewTab(policyNumber)
                .savePDF(testcaseFormattedID).verifyPdfContent().saveOption(policyNumber);
    }

    @Test(description = "QA Hospital Binder", groups = { "BTS Smoke Test" }, priority = 5)
    public void TC42242() throws Exception {
        LoginPageDTO lpDTO;
        LoginPage loginpage;
        ExcelUtil exlUtil = new ExcelUtil();
        RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
        lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
        PolicyIndicationPageDTO indicationPageDTO = new PolicyIndicationPageDTO(TestCaseDetails.testDataDictionary);
        loginpage = new LoginPage(driver);
        loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
                .searchPolicyPolicyQuotePageBTS_QA();
        rateapolicyPage.AcceptFromActionDropDown().identifyPhase(indicationPageDTO.policyPhaseValue).billingSetup()
                .refreshCurrentPage(driver).coverageDetailsSelect();
        String policyNumber = rateapolicyPage.policyNo();
        rateapolicyPage.coverageUpdates(policyNumber).rateFunctionalityWithoutPremiumAmountVerification(policyNumber)
                .clickPreviewTab(policyNumber).savePDF(testcaseFormattedID).verifyPdfContent().saveOption(policyNumber);
        exlUtil.writeData("TC42665", "PolicyNum", policyNumber, 1, ExcelPath);

    }

    @Test(description = "QA Hospital Issue Policy Forms", groups = { "BTS Smoke Test" }, priority = 6)
    public void TC42665() throws Exception {
        LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
        PolicyQuotePageDTO policyquotepagedto = new PolicyQuotePageDTO(TestCaseDetails.testDataDictionary);
        LoginPage loginpage = new LoginPage(driver);
        ExcelUtil exlUtil = new ExcelUtil();
        PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);
        loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromPolicyBinderPage()
                .searchPolicyPolicyQuotePageBTS_QA();
        PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
        String policyNumber = policybinderpage.policyNo();
        policybinderpage.endorsementFromActionDropDown().endorsePolicy(policyNumber)
                .identifyPhase(policyquotepagedto.policyPhaseValue).rateFunctionality(policybinderpage.policyNo());
        String policyNo = policybinderpage.policyNo();
        policyQuotePage.clickPreviewTab(policyNo).savePDF(testcaseFormattedID).verifyPdfContent();
        policybinderpage.saveOption(policyNo);
        /*
         * exlUtil.writeData("TC43783", "PolicyNum", policyNo, 1, ExcelPath);
         * exlUtil.writeData("TC43773", "PolicyNum", policyNo, 1, ExcelPath);
         * exlUtil.writeData("TC43775", "PolicyNum", policyNo, 1, ExcelPath);
         * exlUtil.writeData("TC43776", "PolicyNum", policyNo, 1, ExcelPath);
         * exlUtil.writeData("TC43781", "PolicyNum", policyNo, 1, ExcelPath);
         * exlUtil.writeData("TC43777", "PolicyNum", policyNo, 1, ExcelPath);
         */
        exlUtil.writeData("TC42399", "PolicyNum", policyNo, 1, ExcelPath);
        exlUtil.writeData("TC42240", "PolicyNum", policyNo, 1, ExcelPath);
        exlUtil.writeData("TC42400", "PolicyNum", policyNo, 1, ExcelPath);
        exlUtil.writeData("TC42666", "PolicyNum", policyNo, 1, ExcelPath);
        exlUtil.writeData("TC42245", "PolicyNum", policyNo, 1, ExcelPath);
        exlUtil.writeData("TC42250", "PolicyNum", policyNo, 1, ExcelPath);
    }

    @Test(description = "QA FM - Hospital Verify On Demand Invoice, Create Batch and Post Batch", groups = {
            "BTS Smoke Test" }, priority = 7)
    public void TC42250() throws Exception {
        String Empty = "";
        LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
        LoginPage loginpage = new LoginPage(driver);
        FinancePageDTO financePageDTO = new FinancePageDTO(TestCaseDetails.testDataDictionary);
        ExcelUtil exlUtil = new ExcelUtil();
        loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToFinanceHomePage()
                .searchPolicyOnFinanceHomePage().openFirstAccount().onDemandInvoice().exportExcelSheet("SampleFile")
                .getInvoiceAmountFromExcel().cashEntry().batchFunction().validateBatch().postBatchFunctionality()
                .donwloadFinalSheetBySearchingAccountNo();
        exlUtil.writeData("TC42248", "PolicyNum", financePageDTO.policyNum, 1, ExcelPath);
    }

    @Test(description = "FM - Hospital Verify Credit Applications", groups = { "BTS Smoke Test" }, priority = 8)
    public void TC42248() throws Exception {
        // To reuse canceled UMBPL -reinstate the UMB PL coverage by selecting
        // the coverage UMB PL
        // and click Policy Actions>Reinstate, then click rate and then save as
        // official,
        FinancePage financepage = new FinancePage(driver);
        RateApolicyPage rateAPolicyPage = new RateApolicyPage(driver);
        LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
        LoginPage loginpage = new LoginPage(driver);
        FinancePageDTO financePageDTO = new FinancePageDTO(TestCaseDetails.testDataDictionary);
        loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToFinancePageFromHeaderLink()
                .receivableDownload(financePageDTO.CreditInstallmentBeforeFileName).naviagetToPolicyFromHeaderLink()
                .searchPolicyRateAPolicyPage().coverageDetailsSelect();
        financepage.selectUMBCoverage().selectCancelFromPolicyActionDDL().rateFunctionalityWithoutPremiumVerification()
                .openPDF(rateAPolicyPage.policyNo()).savePDF(testcaseFormattedID);
        financepage.savePolicyAsofficial().navigateToFinancePageFromHeaderLink().searchPolicyOnFinanceHomePage()
                .openFirstAccount().downloadExcel(financePageDTO.CancelledCoverageTransactionFileName)
                .receivableDownloaWithoutNavigationForQA(financePageDTO.CreditInstallmentAfterFileName);
    }

    /*
     * @Test(description = "QA Hospital Verify Attach Form", groups = {
     * "BTS Smoke Test" }, priority = 9) public void TC42399() throws Exception
     * { LoginPageDTO lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); LoginPage loginpage =
     * new LoginPage(driver); ExcelUtil exlUtil = new ExcelUtil();
     * loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).naviagetToPolicyFromHeaderLink(); HomePage homepage = new
     * HomePage(driver); String policyNum =
     * homepage.policySearchUsingSearchCriteria(); RateApolicyPage
     * rateapolicyPage = new RateApolicyPage(driver); String policyNumber =
     * rateapolicyPage.checkPolicyViewModeAndUpdateCoverage(policyNum);
     * exlUtil.writeData("TC43774", "PolicyNum", policyNumber, 1, ExcelPath);
     * PolicyBinderPage pbp = new PolicyBinderPage(driver); String policyNo =
     * pbp.policyNo();
     * rateapolicyPage.rateFunctionality(policyNo).clickPreviewTab(policyNo).
     * savePDF(testcaseFormattedID) .verifyPdfContent(); }
     * 
     * @Test(description = "Hospital Verify Interactive Form", groups = {
     * "BTS Smoke Test" }, priority = 10) public void TC42247() throws Exception
     * { LoginPageDTO lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); LoginPage loginpage =
     * new LoginPage(driver); RateApolicyPage rateapolicypage = new
     * RateApolicyPage(driver); loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
     * .searchPolicyRateAPolicyPage(); String policyNo =
     * rateapolicypage.policyNo();
     * rateapolicypage.coverageDetailSelectForCinCom().cincomFlow(policyNo)
     * .rateFunctionalityWithoutPremiumAmountVerification(policyNo).
     * clickPreviewTab(policyNo)
     * .savePDF(testcaseFormattedID).verifyPdfContent(); }
     * 
     * @Test(description = "QA Hospital Create Claim", groups = {
     * "BTS Smoke Test" }, priority = 11) public void TC42666() throws Exception
     * { LoginPageDTO lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); RateAPolicyPageDTO
     * rpdto = new RateAPolicyPageDTO(TestCaseDetails.testDataDictionary);
     * LoginPage loginpage = new LoginPage(driver); PolicyBinderPage
     * policybinderpage = new PolicyBinderPage(driver); ExcelUtil exlUtil = new
     * ExcelUtil(); loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
     * .searchPolicyRateAPolicyPage(); String clientID =
     * policybinderpage.getClientId();
     * policybinderpage.verifyPhase(rpdto.policyPhaseValue).navigatetoClaimsPage
     * ().getPatientDetails() .enterDataOnClaimsPage(clientID); String
     * claimNumber = policybinderpage.claimNo(); exlUtil.writeData("TC42405",
     * "claimNum", policybinderpage.claimNo(), 1, ExcelPath);
     * exlUtil.writeData("TC42252", "claimNum", policybinderpage.claimNo(), 1,
     * ExcelPath); }
     * 
     * @Test(description = "Hospital Claim - Verify Change Claim Status", groups
     * = { "BTS Smoke Test" }, priority = 22) public void TC42405() throws
     * Exception { LoginPage loginpage = new LoginPage(driver); LoginPageDTO
     * lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
     * RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
     * PolicyBinderPage policybinderpage = null;
     * loginpage.loginToeOasis(lpDTO.username, lpDTO.password); policybinderpage
     * = new PolicyBinderPage(driver);
     * policybinderpage.navigatetoClaimsPage().searchClaim().statusChange(
     * rateapolicyPage.policyNo()); }
     * 
     * @Test(description = "Claims - Enter Transactions", groups = {
     * "BTS Smoke Test" }, priority = 13) public void TC42252() throws Exception
     * { LoginPageDTO lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); LoginPage loginpage =
     * new LoginPage(driver); loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToClaimsPageFromHomePageLink().searchClaim()
     * .openTransactionTab().addTransactionDataAndSaveTransaction(); }
     * 
     * @Test(description = "Verify Hospital Preview Forms", groups = {
     * "BTS Smoke Test" }, priority = 14) public void TC42240() throws Exception
     * { RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
     * LoginPageDTO lpDTO; LoginPage loginpage; PolicyQuotePage policyquotepage
     * = new PolicyQuotePage(driver); lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); loginpage = new
     * LoginPage(driver); loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
     * .searchPolicyRateAPolicyPage();
     * rateapolicypage.policyEndorsement(rateapolicypage.policyNo()); String
     * policyNumber = rateapolicypage.policyNo();
     * rateapolicypage.rateFunctionality(policyNumber).clickPreviewTab(
     * policyNumber).savePDF(testcaseFormattedID) .verifyPdfContent();
     * policyquotepage.saveOptionOfficial(policyNumber);
     * 
     * }
     * 
     * @Test(description = "QA Hospital Copy to Quote", groups = {
     * "BTS Smoke Test" }, priority = 15) public void TC43781() throws Exception
     * { ExcelUtil exlUtil = new ExcelUtil(); LoginPageDTO lpDTO; LoginPage
     * loginpage; PolicyBinderPage policybinderpage = new
     * PolicyBinderPage(driver); RateApolicyPage rateApolicyPage = new
     * RateApolicyPage(driver); PolicyQuotePage policyQuotePage = new
     * PolicyQuotePage(driver); PolicySubmissionPage policySubmissionPage = new
     * PolicySubmissionPage(driver); PolicyQuotePageDTO policyquotepagedto = new
     * PolicyQuotePageDTO(TestCaseDetails.testDataDictionary); lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); loginpage = new
     * LoginPage(driver); loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).naviagetToPolicyFromHeaderLink()
     * .searchPolicyRateAPolicyPage();
     * 
     * String policyNumber = policybinderpage.policyNo();
     * 
     * policybinderpage.copyToQuoteFromActionDropDown(policyNumber); // For QA
     * policyQuotePage.copyFromActionDropDownForQAWithoutBackup();
     * policySubmissionPage.changePhaseToIndicationAndAddQuoteDescription();
     * rateApolicyPage.rateFunctionality(policybinderpage.policyNo());
     * policyQuotePage.saveOptionOfficial(policybinderpage.policyNo()).
     * copyFromActionDropDownForQAWithoutBackup()
     * .changePolicyPhase(policyquotepagedto.quotePhaseValue);
     * rateApolicyPage.rateFunctionality(policybinderpage.policyNo());
     * policyQuotePage.saveOptionOfficial(policybinderpage.policyNo());
     * rateApolicyPage.AcceptFromActionDropDownwithoutBackupPolicy().
     * billingSetup().refreshCurrentPage(driver)
     * .rateFunctionality(policybinderpage.policyNo()).saveOptionOfficial(
     * policybinderpage.policyNo());
     * policybinderpage.endorsementFromActionDropDownwithoutBackupPolicy()
     * .endorseAPolicyforRateApolicyPage(policyNumber).rateFunctionality(
     * policybinderpage.policyNo()); String policyNumb =
     * policybinderpage.policyNo();
     * policyQuotePage.clickPreviewTab(policyNumb).savePDF(testcaseFormattedID);
     * policyQuotePage.saveOptionOfficial(policyNumb);
     * exlUtil.writeData("TC42243", "PolicyNum", policybinderpage.policyNo(), 1,
     * ExcelPath); exlUtil.writeData("TC42246", "PolicyNum",
     * policybinderpage.policyNo(), 1, ExcelPath); }
     * 
     * @Test(description = "Hospital Verify Image Right", groups = {
     * "BTS Smoke Test" }, priority = 16) public void TC42243() throws Exception
     * { LoginPageDTO lpDTO; LoginPage loginpage; FindPolicyPage findapolicypage
     * = new FindPolicyPage(driver); RateApolicyPage rateapolicypage = new
     * RateApolicyPage(driver); rateapolicypage.openImageRight(); lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); loginpage = new
     * LoginPage(driver); loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink();
     * findapolicypage.openSearchPolicyPane().openSearchPolicyPane().
     * selectTermStatusAndIssueCompany()
     * .selectPolicyType().searchFromFindPolicyPage().
     * selectValueFromActionDropDown().ImageRightFocus(); }
     * 
     * @Test(description = "FM - Hospital Verify FM Installment", groups = {
     * "BTS Smoke Test" }, priority = 17) public void TC42246() throws Exception
     * { LoginPageDTO lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); LoginPage loginpage =
     * new LoginPage(driver); HomePage homePage = new HomePage(driver);
     * FinancePage financePage = new FinancePage(driver); FinancePageDTO
     * financePageDTO = new FinancePageDTO(TestCaseDetails.testDataDictionary);
     * PolicyIndicationPage policyIndicationPage = new
     * PolicyIndicationPage(driver); RateApolicyPage rateApolicyPage = new
     * RateApolicyPage(driver); PolicyQuotePage policyQuotePage = new
     * PolicyQuotePage(driver); loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToFinanceHomePage()
     * .searchPolicyOnFinanceHomePage().openFirstAccount().onDemandInvoice()
     * .exportExcelSheet(financePageDTO.onDemandInvoiceInstallmentExcel)
     * .selectReceivableTabAndExportExcel(financePageDTO.
     * onDemandInvoiceInstallementBeforeExcel)
     * .selectAccountTabInvoicesButtonAndExportExcel().
     * navigateToPolicyPageThroughPolicyHeaderLink()
     * .policySearchUsingSearchCriteria(); String cellValue =
     * financePage.readDataFromExcelSheet(financePageDTO.dataSheetName,
     * financePageDTO.testDataColumnName, financePageDTO.dataRowNumber,
     * financePageDTO.exportedExcelSheetName);
     * financePage.writeDataInExcelSheet(cellValue,
     * financePageDTO.TCSheetNumber, financePageDTO.testDataColumnheader,
     * financePageDTO.rowNumber); String policyNum = rateApolicyPage.policyNo();
     * String nextDay = financePage.nextDayOfDueDate();
     * financePage.policyEndorsementWithDate(policyNum,
     * nextDay).selectCoverageTab();
     * financePage.selectCoverageFromGridList().selectAddCoverageButton();
     * policyIndicationPage.selectCoverageFromPopupListAddDatePremium(
     * financePageDTO.retroDate) .closeAddCoverageWindow();
     * rateApolicyPage.rateFunctionalityWithoutPremiumAmountVerification(
     * policyNum).clickPreviewTab(policyNum) .savePDF(financePageDTO.savePDFAs);
     * policyQuotePage.saveOptionOfficial(policyNum);
     * homePage.navigateToFinancePageFromHeaderLink().
     * searchPolicyOnFinanceHomePage().openFirstAccount()
     * .exportExcelSheet(financePageDTO.excelNameAddCoverageInstallment)
     * .selectReceivableTabAndExportExcel(financePageDTO.
     * excelNameOnDemandInvoiceInstallmentAfter); }
     * 
     * @Test(description = "Hospital Renewal", groups = { "BTS Smoke Test" },
     * priority = 18) public void TC42400() throws Exception { LoginPageDTO
     * lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary); LoginPage
     * loginpage = new LoginPage(driver); PolicyQuotePage policyQuotePage = new
     * PolicyQuotePage(driver); PolicyQuotePageDTO policyquotepageDTO = new
     * PolicyQuotePageDTO(TestCaseDetails.testDataDictionary); PolicyBinderPage
     * policyBinderPage = new PolicyBinderPage(driver);
     * loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
     * .searchPolicyPolicyQuotePage().selectPolicyAction().addQuoteDescription()
     * .save_CaptureTransactionDetails();
     * 
     * String PolicyNo = policyBinderPage.policyNo();
     * policyQuotePage.saveOptionAndCaptureTransactionDetails(policyquotepageDTO
     * .saveAsPolicyDDLValue, PolicyNo); String policyNum =
     * policyBinderPage.policyNo();
     * policyQuotePage.saveOptionOfficial(policyNum).applyChanges()
     * .changePolicyPhase(policyquotepageDTO.policyPhaseValue).
     * saveOptionOfficial(PolicyNo); }
     * 
     * @Test(description = "FM - Verify that user can create an account in FM",
     * groups = { "BTS Smoke Test" }, priority = 19) public void TC42251()
     * throws Exception {
     * 
     * LoginPageDTO lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); LoginPage loginpage =
     * new LoginPage(driver); loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToFinancePageFromHeaderLink().newAccountOpen()
     * .entitySelectSearch().selectIssueCompany().selectAddress().
     * saveAccountDetails() .captureSaveScreenshotofMantainAccountpage(); }
     * 
     * @Test(description =
     * "Claims - Verify that user is allowed to change Billing Parameter for an Existing Account"
     * , groups = { "BTS Smoke Test" }, priority = 20) public void TC42403()
     * throws Exception { LoginPageDTO lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); LoginPage loginpage =
     * new LoginPage(driver); loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToFinancePageFromHeaderLink()
     * .searchAccountUsingSearchCriteria().selectLastAccountFromAccountList().
     * maintainAccount()
     * .saveAccountInformation().captureSaveScreenshotofMantainAccountpage(); }
     * 
     * // @Test(description = "Hospital - Add multiple risks", groups = { "BTS
     * // Smoke Test" }, priority = 21) public void TC42244() throws Exception {
     * String Blank = ""; LoginPageDTO lpDTO = new
     * LoginPageDTO(TestCaseDetails.testDataDictionary); LoginPage loginpage =
     * new LoginPage(driver); RateApolicyPage rateapolicypage = new
     * RateApolicyPage(driver); PolicyBinderPage policyBinderPage = new
     * PolicyBinderPage(driver); PolicyQuotePage policyQuotePage = new
     * PolicyQuotePage(driver); PolicyIndicationPage policyIndicationPage = new
     * PolicyIndicationPage(driver); PolicySubmissionPage policySubmissionPage =
     * new PolicySubmissionPage(driver); PolicyQuotePageDTO policyQuotePageDTO =
     * new PolicyQuotePageDTO(TestCaseDetails.testDataDictionary);
     * PolicyIndicationPageDTO indicationPageDTO = new
     * PolicyIndicationPageDTO(TestCaseDetails.testDataDictionary);
     * loginpage.loginToeOasis(lpDTO.username,
     * lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
     * .searchPolicyPolicyBinderPage().copyToQuoteFromActionDropDown(
     * policyBinderPage.policyNo());
     * policySubmissionPage.copyFromPolicyActionDropDown(policyBinderPage.
     * policyNo());
     * policyQuotePage.changePolicyPhase(policyQuotePageDTO.policyPhaseValue);
     * String policyNo = policyBinderPage.policyNo();
     * 
     * for (int i = 0; i < indicationPageDTO.riskTypeValue.size(); i++) {
     * 
     * if (indicationPageDTO.riskTypeValue.get(i).equals(Blank)) { break; } else
     * if (indicationPageDTO.riskTypeValue.get(i).equals("Dentist")) {
     * policyIndicationPage.openLimitSharingTab(policyNo).addSharedGroup(
     * policyNo).closeLimitSharingtab(); }
     * 
     * policyIndicationPage.selectRiskTab()
     * .selectRiskTypeFromPopupWindowAndSelectEntity(policyNo,
     * indicationPageDTO.riskTypeValue.get(i),
     * indicationPageDTO.riskEntityName.get(i))
     * .addRiskInformation(indicationPageDTO.riskCounty.get(i),
     * indicationPageDTO.riskSpeciality.get(i),
     * indicationPageDTO.riskTypeValue.get(i), indicationPageDTO.FTEType.get(i))
     * .selectCoverageTab().selectCoverageFromCoverageList()
     * .addRetroactiveDate(indicationPageDTO.riskTypeValue.get(i)); }
     * rateapolicypage.rateFunctionality(policyNo).saveOptionOfficial(policyNo);
     * rateapolicypage.AcceptFromActionDropDown().isAlertPresent().identifyPhase
     * (indicationPageDTO.policyPhaseValue)
     * .billingSetup().refreshCurrentPage(driver); String policyNum =
     * policyBinderPage.policyNo();
     * rateapolicypage.rateFunctionality(policyNum).saveOptionOfficial(policyNum
     * ); rateapolicypage.policyEndorsementwithoutBackupPolicy(policyNum)
     * .identifyPhase(indicationPageDTO.policyPhaseValue2).rateFunctionality(
     * policyNum) .clickPreviewTab(policyNum).savePDF(testcaseFormattedID);
     * policyQuotePage.saveOptionOfficial(policyNum); }
     */

    @AfterMethod(alwaysRun = true)
    public void logoffFromAppclication(ITestResult result) throws IOException, InterruptedException, URISyntaxException,
            IllegalArgumentException, IllegalAccessException, SecurityException {
        ExtentReporter.report.endTest(ExtentReporter.logger);
        HomePage homepage = new HomePage(driver);
        if (ITestResult.FAILURE == result.getStatus()) {
            verdict = "Fail";
            try {
                String screenshotLocation = screenshotfolderpath + result.getName() + ".png";
                ExtentReporter.logger.log(LogStatus.FAIL, ExtentReporter.logger.addScreenCapture(screenshotLocation));
                ExtentReporter.logger.log(LogStatus.FAIL, result.getThrowable());
                // homepage.logoutFromeOasis();
            } catch (Exception e) {
                System.out.println("Error in repot");
                e.printStackTrace();
            }

        } else if (ITestResult.SUCCESS == result.getStatus()) {
            verdict = "Pass";
            homepage.logoutFromeOasis();
        } else if (ITestResult.SKIP == result.getStatus()) {
            verdict = "Hold";
        }
        // ExtentReporter.report.flush();
        // "Blocked", "Deferred", "Enhancement", "Error", "Fail", "Hold", "In
        // Progress", "Inconclusive", "Invalid", "Out of Scope", "Pass",
        // "Waiting for Policy"
        if (!ExtentReporter.excelPath.equals("")) {
            ExtentReporter.excelPath = ExtentReporter.excelPath.substring(0, ExtentReporter.excelPath.lastIndexOf(";"));
        }
        iR.updateResultsInRally(serverURL, username, password, workspace, project, testcaseFormattedID.toUpperCase(),
                buildNumber, notes, userRef, duration, verdict, ExtentReporter.excelPath);

        Thread.sleep(2000);
        driver.quit();
    }

    //
    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        /*
         * Another solution to run failed test cases
         * 
         * FailedTCRerun failedtcrun = new FailedTCRerun();
         * failedtcrun.reRunFailedTC();
         */
        ExtentReporter.report.flush();
        ExtentReporter.report.close();
        if (driver != null) {
            driver.quit();
        }
    }
}
