package test;

import utilities.GmailUtility;

public class SendEmail {

        public static void main(String[] args) {

            // your google account credentials:
            String userName = "Cybertek.Batch@gmail.com";
            String passWord = "Cybertek2021Batch23";

            // email info:
            String[] receivers = {

            };

            String subject ="Thank you";
            String MSG ="Hello Muhtar. \nThank you for all the work you do, you are an awesome teacher! \nRegards, Inesa";

            // method call:
            int count = 1;
            for(int i = 1; i<= count; i++) {
                for (String receiver : receivers) {
                    GmailUtility.sendEmails(userName, passWord, receiver, subject, MSG);
                    System.out.println("Send "+i+" email to "+receiver);
                }

            }

            System.out.println("Completed");


        }

    }

 /*
In order to use your own gmail account:
    first Step:
        go to:
            https://myaccount.google.com/lesssecureapps?utm_source=google-account&utm_medium=web

                Make sure that your google account allows third party app
*/
