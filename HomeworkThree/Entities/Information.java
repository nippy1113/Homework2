package HomeworkThree.Entities;

import HomeworkThree.Entities.Company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Information {
    List<Company> companies;

    public void OutputAllCompanies() {
        System.out.println("Companies: ");
        for (int i = 0; i < companies.size(); i++) {
            System.out.println(companies.get(i).name + "-" +
                    companies.get(i).founded);
        }
    }

    public void OutputAllCompanies(String inputString) throws ParseException {  //Метод поочередно сравнивает введенную в параметре
        Date date;                                                                 //дату на предмет совпадения с одним из необходимых
        Date inputDate;                                                            //форматов. затем уже сравнивает дату с теми что внутри
                                                                                   // массива и выводит информацию о компаниях основаных
                                                                                   // после введеной даты.
                                                                                   // если введеная страка соответствует одному из кодов валюты
                                                                                   // то метод выводит уже об id компании и кодах ценных бумаг содержащих такую валюту

        if (inputString.matches("\\d{2}\\.\\d{2}\\.\\d{4}")
        ||   inputString.matches("\\d{2}\\/\\d{2}\\/\\d{4}")) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            inputString = inputString.replace('/','.');
            inputDate = format.parse(inputString);
            System.out.println("Companies that founded after the input date: ");
            for (int i = 0; i < companies.size(); i++) {
                date = format.parse(companies.get(i).founded);
                if (date.after(inputDate)) {
                    System.out.println(companies.get(i).name + " " + companies.get(i).founded);
                }
            }
        }

        if (inputString.matches("\\d{2}\\.\\d{2}\\,\\d{2}")
        ||  inputString.matches("\\d{2}\\/\\d{2}\\/\\d{2}")) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");
            inputString = inputString.replace('/','.');
            inputString = inputString.replace(',','.');
            inputDate = format.parse(inputString);
            System.out.println("Companies that founded after the input date: ");
            for (int i = 0; i < companies.size(); i++) {
                date = format.parse(companies.get(i).founded);
                if (date.after(inputDate)) {
                    System.out.println(companies.get(i).name + " " + companies.get(i).founded);
                }
            }
        }

        if (inputString.matches("[A-Z]{2,3}")) {
            System.out.println("ID's of the Companies that uses this currency and codes of the securities that contains that currency");
            for (int i = 0; i < companies.size(); i++) {
                System.out.println("Company id: " + companies.get(i).id + "(" + companies.get(i).name + ")");
                for (int j = 0; j < companies.get(i).securities.size(); j++) {
                    for (int k = 0; k < companies.get(i).securities.get(j).currency.size(); k++) {
                        if (companies.get(i).securities.get(j).currency.get(k).equals(inputString)) {
                            System.out.println(companies.get(i).securities.get(j).code);
                        }
                    }
                }
                System.out.println();
            }
        }
     }

    public void OutputOverdueSecurities() throws ParseException {
        Date securityDate;
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        int overdueSecuritiesCount = 0;

        System.out.println("Overdue Securities: ");
        for (int i = 0; i < companies.size(); i++) {
            System.out.println("Overdue securities of the " + companies.get(i).name + " Company: ");
            for (int j = 0; j < companies.get(i).securities.size(); j++) {
                securityDate = format.parse(companies.get(i).securities.get(j).date);
                if (securityDate.before(currentDate)) {
                    System.out.println(companies.get(i).securities.get(j).code + " " +
                            companies.get(i).securities.get(j).date + " " +
                            companies.get(i).securities.get(j).name);
                    overdueSecuritiesCount++;
                }
            }
            System.out.println();
        }
        System.out.println("total amount of overdue securities: " + overdueSecuritiesCount);
    }
}
