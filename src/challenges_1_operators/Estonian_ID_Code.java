/* wikist:
    isikukood = 11 numbrit;
    1.: sugu + sünnisajand (1–8);
    2.–7.: sünnikuupäev (AAKKPP);
    8.–10.: seerianumber;
    11.: kontrollnumber, arvutatud kaalutud algoritmi abil; */

package challenge.task_1_operators;
// ülesandest: Saad sellest meetodi teha, kui see sulle mugav on.
public class Estonian_ID_Code {
    private static int mod11Weighted(String id, int[] weights) {
        int sum = 0; // loendur -> arv × kaal
        for (int i = 0; i < 10; i++) { // võtme 10 esimest numbrit summ-summult
            int digit = Character.getNumericValue(id.charAt(i)); // teisenda stringi number numbriks 0–9
            System.out.println("digit = " + digit);

            sum += digit * weights[i]; // korrutage vastava kaaluga ja lisage kogusummale
        }
        // 1) kui summ < 10 — potentsiaalne kontrollnumber;
        // 2) kui summ == 10 — korrake arvutust teise kaalude komplektiga (II tase);
        // 3) kui see on jälle 10, loetakse kontrollnumber nulliks.
        return sum % 11;
    }

    public static void main(String[] args) {
        // ülesandest: võtab sisse stringmuutuja koos Eesti ID-koodiga

        // 3*1 + 9*2 + 5*3 + 0*4 + 1*5 + 2*6 + 3*7 + 4*8 + 2*9 + 1*1
        // = 3 + 18 + 15 + 0 + 5 + 12 + 21 + 32 + 18 + 1
        // = 125 % 11 = 4 (kontrollnumber)

        String id = "39501234215"; // sellepärast see ID code on vale
        System.out.println("ID = " + id);

        // peab olema 11 numbrit
        if (id == null || id.length() != 11) {
            System.out.println("NOT valid (length)");
            return;
        }

        // peab olema ainult numbrid
        if (!id.matches("\\d+")) {
            System.out.println("NOT valid (non-digit chars)");
            return;
        }

        // kontrollime, et 'if' töötab õigesti
        System.out.println("Format OK");

        // wikist: kasutame kaks erinevat kaalumist, kui esimene annab tulemuseks 10
        int[] w1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 1 };
        int[] w2 = { 3, 4, 5, 6, 7, 8, 9, 1, 2, 3 };

        // esimene kontrollkäik
        int control = mod11Weighted(id, w1);
        System.out.println("control=" + control);

        // võrdleme kontrolli ja ID koodi viimast numbrit
        // kui on 10, siis teine kontrollkäik
        if (control == 10) {
            control = mod11Weighted(id, w2);
            if (control == 10)
                control = 0;
        }

        int lastDigit = id.charAt(10) - '0';
        System.out.println("lastDigit=" + lastDigit);

        // ülesandest: annab kasutajale teada, kas see ID-kood on õige või mitte
        if (lastDigit == control) {
            System.out.println("ID code is valid");
        } else {
            System.out.println("ID code is NOT valid");
        }

    }
}