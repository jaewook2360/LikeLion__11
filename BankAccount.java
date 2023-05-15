import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BankAccount {

    public static void main(String[] args) {
        ArrayList<HashMap<String, String>> users = new ArrayList<HashMap<String, String>>();
        boolean register = false;

        while (!register) {
            Scanner sc = new Scanner(System.in);
            System.out.println("=========Bank Menu=========");
            System.out.println("1. 계좌개설");
            System.out.println("2. 입금하기");
            System.out.println("3. 출금하기");
            System.out.println("4. 전체조회");
            System.out.println("5. 계좌이체");
            System.out.println("6. 프로그램 종료");
            System.out.println("===========================");
            System.out.print("입력: ");
            String register_input = sc.nextLine();

            if (register_input.equals("1")) {
                System.out.println("======계좌개설======");
                HashMap<String, String> user = new HashMap<String, String>();
                System.out.println("계좌번호: ");
                String accountNumber = sc.nextLine();
                System.out.println("이름: ");
                String name = sc.nextLine();
                System.out.println("예금: ");
                int deposit = Integer.parseInt(sc.nextLine());
                if (deposit <= 0) {
                    System.out.println("올바른 금액을 입력해주세요.");
                } else {
                    user.put("accountNumber", accountNumber);
                    user.put("name", name);
                    user.put("deposit", String.valueOf(deposit));
                    users.add(user);
                    System.out.println("##계좌개설을 완료하였습니다##");
                    System.out.println("===================");
                }
            } else if (register_input.equals("2")) {
                System.out.println("======입금하기======");
                HashMap<String, String> user = new HashMap<String, String>();
                System.out.println("계좌번호: ");
                String accountNumber = sc.nextLine();

                System.out.println("이름: ");
                String name = sc.nextLine();

                System.out.println("예금: ");
                int deposit = Integer.parseInt(sc.nextLine());

                System.out.println("입금하실 계좌번호를 입력해주세요: ");
                String number_input = sc.nextLine();
                if (number_input.equals(accountNumber)) {
                    System.out.print("계좌이름: ");
                    System.out.println(name);
                    System.out.print("계좌잔고: ");
                    System.out.printf("%d원", deposit);

                    System.out.println("입금하실 금액을 입력해주세요: ");


                    int money = Integer.parseInt(sc.nextLine());
                    if (money <= 0) {
                        System.out.println("올바른 금액을 입력해주세요.");
                    } else {
                        deposit += money;
                        user.put("deposit", String.valueOf(deposit));
                        user.put("accountNumber", accountNumber);
                        user.put("name", name);
                        users.add(user);
                        System.out.println("\n");
                        System.out.println("##계좌잔고: " + deposit + "원##");
                        System.out.println("##입금이 완료되었습니다##");
                        System.out.println("================");
                    }

                } else{
                    System.out.println("================");
                    System.out.println("계좌번호를 다시 입력해주세요");
                    System.out.println("================");
                }
            } else if (register_input.equals("3")) {
                System.out.println("======출금하기======");
                HashMap<String, String> user = new HashMap<String, String>();
                System.out.println("계좌번호: ");
                String accountNumber = sc.nextLine();
                System.out.println("이름: ");
                String name = sc.nextLine();
                System.out.println("출금하실 계좌번호를 입력해주세요: ");
                String number_input = sc.nextLine();

                if (number_input.equals(accountNumber)) {
                    int deposit = Integer.parseInt(sc.nextLine());

                    System.out.print("계좌이름: ");
                    System.out.println(name);

                    System.out.print("계좌잔고: ");
                    System.out.println(deposit + "원");

                    System.out.println("출금하실 금액을 입력해주세요: ");
                    int withdraw = Integer.parseInt(sc.nextLine());

                    if (withdraw <= 0) {
                        System.out.println("올바른 금액을 입력해주세요.");
                    } else if (withdraw > deposit) {
                        System.out.println("잔액이 부족합니다. 현재 계좌잔고 : " + deposit + "원");
                    } else {
                        deposit -= withdraw;
                        user.put("deposit", String.valueOf(deposit));
                        user.put("accountNumber", accountNumber);
                        user.put("name", name);
                        users.add(user);
                    }
                    System.out.println("\n");
                    System.out.println("##계좌잔고: " + deposit + "원##");
                    System.out.println("##출금이 완료되었습니다##");
                    System.out.println("================");
                } else {
                    System.out.println("================");
                    System.out.println("계좌번호를 다시 입력해주세요");
                    System.out.println("================");
                }
            } else if (register_input.equals("4")) {
                System.out.println("======전체조회======");

                for (HashMap<String, String> user : users) {
                    System.out.println("계좌번호: " + user.get("accountNumber") + "/" + "이름: " + user.get("name") + "/" + "잔액: " + user.get("deposit") + "원");
                    System.out.println("==================");
                } if (register_input.equals("6")) {
                    System.out.println("##프로그램을 종료합니다##");

                    System.exit(0);
                } else {
                    System.out.println("======잘못된 입력입니다======");
                }

            }
        }
    }
}
