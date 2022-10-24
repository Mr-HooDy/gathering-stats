import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Input password: ");
        String pass = in.nextLine();

        System.out.print("Input date from: ");
        int startDate = in.nextInt();
        System.out.print("Input date to: ");
        int endDate = in.nextInt();

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String email = "vladzazorin@yandex.ru";
        ArrayList<String> saratovList = new ArrayList<>();
        ArrayList<String> otherList = new ArrayList<>();


        WebDriverWait wait = new WebDriverWait(driver, 45);
        
        try {
            driver.get("https://metrika.yandex.ru/stat/visor?period=week&filter=(EXISTS+ym%3Au%3AuserID+WITH+(ym%3Au%3AfirstTrafficSource%3D%3D%2527organic%2527))+and+(ym%3As%3AuserVisits%3D%3D1)&id=49867378");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button")));
            driver.findElement(By.xpath("//button")).click();

            driver.findElement(By.xpath("//input[@id='passp-field-login']")).sendKeys(email);
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='passp-field-passwd']")));

            driver.findElement(By.xpath("//input[@id='passp-field-passwd']")).sendKeys(pass);
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".show-more__button")));
            driver.findElement(By.cssSelector(".show-more__button")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".i-ua_js_yes")));
            for (int i = 1; i <= 6; i++) { //don't touch this!
                driver.findElement(By.cssSelector(".i-ua_js_yes")).sendKeys(Keys.END);
                Thread.sleep(1000);
            }

            String rowLink;
            String city;
            String pageLink;
            boolean isRowLinkPresent;
            boolean isCitySaratov;
            boolean isNotHomePageLink;
            boolean cityElementContainsSymbols;
            WebElement rowLinkElement;
            WebElement rowCityElement;
            WebElement rowPageLinkElement;

            for (int i = startDate; i >= endDate; i--) {
                /*if (i == 191 ||  i == 96 || i == 70 ) {
                    System.out.println("---");
                }*/
                rowLink = "//tr[contains(@class, 'table__body-row')][" + i + "]//td[contains(@class, 'table__td_n_7')]//a[contains(@class, 'link_external_yes')]";
                city = "//tr[contains(@class, 'table__body-row')][" + i + "]//*[@class='humanize-attributes__dimension-name' or text()='Не определено']";
                pageLink = "//tr[contains(@class, 'table__body-row')][" + i + "]//a[contains(@class, 'humanize-attributes__link')]";
                isRowLinkPresent = driver.findElements(By.xpath(rowLink)).size() > 0;
                isCitySaratov = driver.findElement(By.xpath(city)).getAttribute("innerHTML").contains("Саратовская обл");
                rowPageLinkElement = driver.findElement(By.xpath(pageLink));
                isNotHomePageLink = rowPageLinkElement.getAttribute("innerHTML").length() > 14;

                rowCityElement = driver.findElement(By.xpath(city));
                cityElementContainsSymbols = rowCityElement.getAttribute("innerHTML").contains(";");

                String rowPageLinkFull = rowPageLinkElement.getAttribute("innerHTML");
                switch (rowPageLinkFull) {
                    case ("packtime64.ru/otlichiya-akril-i-kauchuk/"): rowPageLinkFull = "отличия акрил каучук";
                            break;
                    case ("packtime64.ru/pnd-pvd-difference/"): rowPageLinkFull = "пвд и пнд разница";
                            break;
                    case ("packtime64.ru/plenki-soldering-pet-pp/"): rowPageLinkFull = "пленки для запайки";
                            break;
                    case ("packtime64.ru/obertochnaya-bumaga/"): rowPageLinkFull = "обёрточная бумага";
                            break;
                    case ("packtime64.ru/vetosh-cp/"): rowPageLinkFull = "ветошь";
                            break;
                    case ("packtime64.ru/p330/"): rowPageLinkFull = "натяжитель b311";
                            break;
                    case ("packtime64.ru/belaya-matovaya-strejch-plenka/"): rowPageLinkFull = "белая матовая стрейч пленка";
                            break;
                    case ("packtime64.ru/rabochie-hb-perchatki/"): rowPageLinkFull = "рабочие хб перчатки";
                            break;
                    case ("packtime64.ru/ipk-500/"): rowPageLinkFull = "контейнер ипк 500";
                            break;
                    case ("packtime64.ru/vosmishovnyy-chernyy/"): rowPageLinkFull = "восьмишовный черный пакет";
                            break;
                    case ("packtime64.ru/p-2/"): rowPageLinkFull = "контейнер p-2";
                            break;
                    case ("packtime64.ru/c-25/"): rowPageLinkFull = "контейнер c-25";
                            break;
                    case ("packtime64.ru/tesa-6256/"): rowPageLinkFull = "клипсатор tesa";
                            break;
                    case ("packtime64.ru/polipropilenovaya-plenka/"): rowPageLinkFull = "пленка пп";
                            break;
                    case ("packtime64.ru/komplekt-5,8-293/"): rowPageLinkFull = "контейнер 5.8";
                            break;
                    case ("packtime64.ru/plenka-pvd-pnd/"): rowPageLinkFull = "пленка пвд пнд";
                            break;
                    case ("packtime64.ru/lodki/"): rowPageLinkFull = "овальные банки";
                            break;
                    case ("packtime64.ru/bottle-1,0/"): rowPageLinkFull = "бутылка 1 л.";
                            break;
                    case ("packtime64.ru/eco-tabox-300/"): rowPageLinkFull = "eco tabox 300";
                            break;
                    case ("packtime64.ru/glove-br/"): rowPageLinkFull = "рукавицы брезентовые";
                            break;
                    case ("packtime64.ru/cup-250c/"): rowPageLinkFull = "крафтовый стакан 250 мл";
                            break;
                    case ("packtime64.ru/cup-100c/"): rowPageLinkFull = "крафтовый стакан 100 мл";
                            break;
                    case ("packtime64.ru/eco-cake-6000/"): rowPageLinkFull = "eco cake 6000";
                            break;
                    case ("packtime64.ru/bred/"): rowPageLinkFull = "хлебные ящики";
                            break;
                    case ("packtime64.ru/eco-prizma-550/"): rowPageLinkFull = "бумажный контейнер";
                            break;
                    case ("packtime64.ru/equipment/"): rowPageLinkFull = "упаковочное оборудование";
                            break;
                    case ("packtime64.ru/meat-fish-milk/"): rowPageLinkFull = "ящики";
                            break;
                    case ("packtime64.ru/trays/"): rowPageLinkFull = "пищевые лотки";
                            break;
                    case ("packtime64.ru/clippers/"): rowPageLinkFull = "клипсаторы";
                            break;
                    case ("packtime64.ru/kontejner-dlya-yagod-250-ml/"): rowPageLinkFull = "контейнер для ягод 250 мл";
                            break;
                    case ("packtime64.ru/sousniki/"): rowPageLinkFull = "контейнер для ягод 250 мл";
                            break;
                    case ("packtime64.ru/fruit-1,8-2/"): rowPageLinkFull = "ящики для фруктов";
                            break;
                    case ("packtime64.ru/kontejner-dlya-yagod-krp-55/"): rowPageLinkFull = "контейнер крп 55";
                            break;
                    case ("packtime64.ru/meshki-dlya-stroitelnogo-musora/"): rowPageLinkFull = "мешки для стоительного мусора";
                            break;
                    case ("packtime64.ru/natyazhitely/"): rowPageLinkFull = "натяжители";
                            break;
                    case ("packtime64.ru/eco-cone-l/"): rowPageLinkFull = "упаковка для картошки фри";
                            break;
                    case ("packtime64.ru/eco-pillow/"): rowPageLinkFull = "упакока для шаурмы";
                            break;
                    case ("packtime64.ru/bottle-0,5/"): rowPageLinkFull = "бутылка 0.5 л";
                            break;
                    case ("packtime64.ru/konteiner-salat/"): rowPageLinkFull = "контейнеры для салата";
                            break;
                    case ("packtime64.ru/perforirovannaya-plenka/"): rowPageLinkFull = "перфорированная пленка";
                            break;
                    case ("packtime64.ru/pet-lenta/"): rowPageLinkFull = "пэт лента";
                            break;
                    case ("packtime64.ru/stretch-plenka/"): rowPageLinkFull = "стрейч пленка";
                            break;
                    case ("packtime64.ru/packages/"): rowPageLinkFull = "пакеты";
                            break;
                    case ("packtime64.ru/bred-1,0/"): rowPageLinkFull = "ящик хлебный";
                            break;
                    case ("packtime64.ru/lotki-i-konteynery-polistirol/"): rowPageLinkFull = "лотки и контейнеры пс";
                            break;
                    case ("packtime64.ru/vetosh-tr/"): rowPageLinkFull = "ветошь";
                            break;
                    case ("packtime64.ru/kartonnye-korobki/"): rowPageLinkFull = "картонные коробки";
                            break;
                    case ("packtime64.ru/termoetiketka/"): rowPageLinkFull = "термоэтикетки";
                            break;
                    case ("packtime64.ru/preimushchestva-upakovki-peht-nad-bops/"): rowPageLinkFull = "преимущества упаковки пэт над бопс";
                            break;
                    case ("packtime64.ru/pet-bottles/"): rowPageLinkFull = "бутылки пэт";
                            break;
                    case ("packtime64.ru/vedra/"): rowPageLinkFull = "пластиковые ведра";
                            break;
                    case ("packtime64.ru/paket-vakuum/"): rowPageLinkFull = "вакуумные пакеты";
                            break;
                    case ("packtime64.ru/porolon/"): rowPageLinkFull = "поролон";
                            break;
                    case ("packtime64.ru/voylok-mebelnyy/"): rowPageLinkFull = "войлок мебельный";
                            break;
                    case ("packtime64.ru/meat-3,20-2/"): rowPageLinkFull = "ящик мясной";
                            break;
                    case ("packtime64.ru/kassovaya-lenta-57-50/"): rowPageLinkFull = "кассовая лента 57";
                            break;
                    case ("packtime64.ru/armir/"): rowPageLinkFull = "армированная лента";
                            break;
                    case ("packtime64.ru/craft-bags/"): rowPageLinkFull = "крафт пакеты";
                            break;
                    case ("packtime64.ru/lenta-pp/"): rowPageLinkFull = "лента пп";
                            break;
                    case ("packtime64.ru/upak-lenta/"): rowPageLinkFull = "упаковочный скотч";
                            break;
                    case ("packtime64.ru/streych-plenka/"): rowPageLinkFull = "стрейч пленка";
                            break;
                    case ("packtime64.ru/bags-pp/"): rowPageLinkFull = "мешки пп";
                            break;
                    case ("packtime64.ru/zapayschiki/"): rowPageLinkFull = "запайщики пакетов";
                            break;
                    case ("packtime64.ru/pergament/"): rowPageLinkFull = "пергамент";
                            break;
                    case ("packtime64.ru/polurukav-450-630/"): rowPageLinkFull = "полурукав 450";
                            break;
                    case ("packtime64.ru/paket-pvd-pnd/"): rowPageLinkFull = "пакеты пвд пнд";
                            break;
                    case ("packtime64.ru/hollofajber/"): rowPageLinkFull = "холлофайбер";
                            break;
                    case ("packtime64.ru/types-scotch/"): rowPageLinkFull = "типы скотча";
                            break;
                    case ("packtime64.ru/eggs-pack/"): rowPageLinkFull = "яичная упаковка";
                            break;
                    case ("packtime64.ru/bumazhnye-salfetki/"): rowPageLinkFull = "бумажные салфетки";
                            break;
                    case ("packtime64.ru/office-tapes/"): rowPageLinkFull = "офисные ленты";
                            break;
                    case ("packtime64.ru/plenki-soldering/"): rowPageLinkFull = "пленки для запайки";
                            break;
                    case ("packtime64.ru/сontainers/"): rowPageLinkFull = "контейнеры";
                            break;
                    case ("packtime64.ru/korreks/"): rowPageLinkFull = "коррексы";
                            break;
                    case ("packtime64.ru/odnorazovaya-posuda/"): rowPageLinkFull = "одноразовая посуда";
                            break;
                    case ("packtime64.ru/elect-tapes/"): rowPageLinkFull = "изоленты";
                            break;
                    case ("packtime64.ru/pakety-dlya-shin/"): rowPageLinkFull = "пакеты для шин";
                            break;
                    case ("packtime64.ru/komplekti/"): rowPageLinkFull = "комплекты контейнеры";
                            break;
                    case ("packtime64.ru/mechki-musor/"): rowPageLinkFull = "мусорные мешки";
                            break;
                    case ("packtime64.ru/cork-sc-501/"): rowPageLinkFull = "крышки для бутылок";
                            break;
                    case ("packtime64.ru/cork-38/"): rowPageLinkFull = "крышки для бутылок";
                            break;
                    case ("packtime64.ru/vspenennyj-kauchuk-epdm/"): rowPageLinkFull = "вспененный каучук";
                            break;
                    case ("packtime64.ru/detachable-tape/"): rowPageLinkFull = "отрывная лента";
                            break;
                    case ("packtime64.ru/korobki-dlya-piccy/"): rowPageLinkFull = "коробки для пиццы";
                            break;
                    case ("packtime64.ru/sinteshar/"): rowPageLinkFull = "синтешар";
                            break;
                    case ("packtime64.ru/upakovka-dlya-konditerskih-izdeliy/"): rowPageLinkFull = "упаковка для кондитерских изделий";
                            break;
                    case ("packtime64.ru/konteiner-soldering/"): rowPageLinkFull = "упаковка для кондитерских изделий";
                            break;
                    case ("packtime64.ru/chekovaya-lenta/"): rowPageLinkFull = "чековая лента";
                            break;
                    case ("packtime64.ru/fast-food/"): rowPageLinkFull = "упаковка для фаст фуда";
                            break;
                    case ("packtime64.ru/doy-pak/"): rowPageLinkFull = "дой паки";
                            break;
                    case ("packtime64.ru/catalog/"): rowPageLinkFull = "упаковка";
                            break;
                    case ("packtime64.ru/kraft-bumaga/"): rowPageLinkFull = "крафт бумага";
                            break;
                    case ("packtime64.ru/konteiner-berry/"): rowPageLinkFull = "контейнеры для ягод";
                            break;
                    case ("packtime64.ru/kontejner-dlya-yagod-t6-250-35/"): rowPageLinkFull = "контейнеры для ягод t6";
                            break;
                    case ("packtime64.ru/shpazhki-dlya-shashlyka-bambukovye/"): rowPageLinkFull = "шпажки для шашлыка";
                            break;
                    case ("packtime64.ru/pakety-vpp/"): rowPageLinkFull = "пакеты впп";
                            break;
                    case ("packtime64.ru/air-bubble/"): rowPageLinkFull = "воздушно-пузырьковая пленка";
                            break;
                    case ("packtime64.ru/paket-ziplock/"): rowPageLinkFull = "зип пакеты";
                            break;
                    case ("packtime64.ru/plenka-tu/"): rowPageLinkFull = "пленка термоусадочная";
                            break;
                    case ("packtime64.ru/banki-kruglye/"): rowPageLinkFull = "банки пластиковые";
                            break;
                    case ("packtime64.ru/bumazhnye-stakany/"): rowPageLinkFull = "бумажные стаканы";
                            break;
                    case ("packtime64.ru/plastikovye-poddony/"): rowPageLinkFull = "пластиковые поддоны";
                            break;
                    case ("packtime64.ru/kraft-upakovka/"): rowPageLinkFull = "крафт упаковка";
                            break;
                    case ("packtime64.ru/plenka-dlya-basseyna/"): rowPageLinkFull = "пленка для бассейна";
                            break;
                    case ("packtime64.ru/otlichiya-pvkh-i-pof-plenok/"): rowPageLinkFull = "отличие поф и пвх";
                            break;
                    case ("packtime64.ru/bumazhnaya-upakovka-dlya-fast-fud/"): rowPageLinkFull = "бумажная упаковка для фаст фуд";
                            break;
                    case ("packtime64.ru/pr-t-85/"): rowPageLinkFull = "прт 85";
                            break;
                    case ("packtime64.ru/plenki/"): rowPageLinkFull = "пленки";
                            break;
                    case ("packtime64.ru/malyar/"): rowPageLinkFull = "малярная лента";
                            break;
                    case ("packtime64.ru/big-behg/"): rowPageLinkFull = "биг бэги";
                            break;
                    case ("packtime64.ru/pakety-s-oknom/"): rowPageLinkFull = "пакеты с окном";
                            break;
                    case ("packtime64.ru/lotki-vps/"): rowPageLinkFull = "лотки впс";
                            break;
                    case ("packtime64.ru/stretch-pvh/"): rowPageLinkFull = "стрейч пвх";
                            break;
                    case ("packtime64.ru/vosmishovnyy-korichnevyy/"): rowPageLinkFull = "восьмишовный коричневый дой пак";
                            break;
                    case ("packtime64.ru/vegetables-fruits-berry/"): rowPageLinkFull = "ящики под овощи";
                            break;
                    case ("packtime64.ru/isolon/"): rowPageLinkFull = "изолон";
                            break;
                    case ("packtime64.ru/spanbonds/"): rowPageLinkFull = "спанбонд";
                            break;
                    case ("packtime64.ru/kuboteiner-23/"): rowPageLinkFull = "куботейнер 23";
                            break;
                    case ("packtime64.ru/ukryvnoy-material/"): rowPageLinkFull = "укрывной материал";
                            break;
                    case ("packtime64.ru/korobka-600-400/"): rowPageLinkFull = "коробка 60 40 40";
                            break;
                    case ("packtime64.ru/alum/"): rowPageLinkFull = "алюминиевая лента";
                            break;
                    case ("packtime64.ru/grids/"): rowPageLinkFull = "сетки мешки";
                            break;
                    case ("packtime64.ru/musornye-kontejnery-i-baki/"): rowPageLinkFull = "мусорные контейнеры и баки";
                            break;
                    case ("packtime64.ru/substrate/"): rowPageLinkFull = "подложки";
                            break;
                    case ("packtime64.ru/chernaya-matovaya-strejch-plenka/"): rowPageLinkFull = "черная матовая стрейч пленка";
                            break;
                    case ("packtime64.ru/termobumaga-top/"): rowPageLinkFull = "термобумага топ";
                            break;
                    case ("packtime64.ru/eco-soup-8c/"): rowPageLinkFull = "eco soup 8c";
                            break;
                    case ("packtime64.ru/plastikovye-bochki/"): rowPageLinkFull = "пластиковые бочки";
                            break;
                    case ("packtime64.ru/kontejner-dlya-odnogo-pirozhnogo-spt-132/"): rowPageLinkFull = "контейнер для одного пирожного";
                            break;
                    case ("packtime64.ru/bottle-5,0/"): rowPageLinkFull = "бутылка 5 л";
                            break;
                    case ("packtime64.ru/vedro-5,8-220/"): rowPageLinkFull = "ведро для шашлыка";
                            break;
                    case ("packtime64.ru/bottle-0,3/"): rowPageLinkFull = "бутылка 0.3 л";
                            break;
                    case ("packtime64.ru/plastikovye-banki-s-kryshkoy/"): rowPageLinkFull = "пластиковые банки с крышкой";
                            break;
                    case ("packtime64.ru/termolenta-58-60-eco/"): rowPageLinkFull = "термолента 58*60";
                            break;
                    case ("packtime64.ru/plenka-bopp/"): rowPageLinkFull = "пленка БОПП";
                            break;
                    case ("packtime64.ru/banki/"): rowPageLinkFull = "банки пластиковые";
                            break;
                    case ("packtime64.ru/kp-9h/"): rowPageLinkFull = "контейнер КР-9Н";
                            break;
                    case ("packtime64.ru/thermocouples/"): rowPageLinkFull = "термочеки";
                            break;
                    case ("packtime64.ru/krt-51/"): rowPageLinkFull = "конейтнер крт-51";
                            break;
                    case ("packtime64.ru/bottle-2,0/"): rowPageLinkFull = "бутылка 2 л";
                            break;
                    default:
                        break;
                }

                if (isNotHomePageLink) {
                    String cityFull = rowCityElement.getAttribute("innerHTML");

                    if (isRowLinkPresent){
                        rowLinkElement = driver.findElement(By.xpath(rowLink));

                        if (isCitySaratov) {
                            System.out.println(rowLinkElement.getAttribute("innerHTML"));
                        } else {
                            if (cityElementContainsSymbols) {
                                cityFull = cityFull.substring(cityFull.lastIndexOf(';') + 2);
                                otherList.add(cityFull + "\t" + rowLinkElement.getAttribute("innerHTML"));
                            } else {
                                otherList.add(rowCityElement.getAttribute("innerHTML") + "\t" + rowLinkElement.getAttribute("innerHTML"));
                            }
                        }

                    } else {
                        if (isCitySaratov) {
                            saratovList.add(rowPageLinkFull);
                        } else if (cityFull.equals("Россия")) {
                            otherList.add(rowCityElement.getAttribute("innerHTML") + "\t" + rowPageLinkFull);
                        } else {
                            cityFull = cityFull.substring(cityFull.lastIndexOf(';') + 2);
                            otherList.add(cityFull + "\t" + rowPageLinkFull);
                        }

                    }
                }
            }

            for(String s : saratovList) {
                System.out.println(s);
            }

            System.out.println("---");

            for(String s : otherList) {
                System.out.println(s);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
