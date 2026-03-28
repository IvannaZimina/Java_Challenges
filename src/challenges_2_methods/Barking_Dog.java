/* --- TASK ---
We have a dog that likes to bark.We need to wake up if the dog is barking at night!
Write a method shouldWakeUp that has 2 parameters.
1 st parameter should be of type boolean and be named barking it represents if our dog is currently barking.
2 nd parameter represents the hour of the day and is of type int with the name hourOfDay and has a valid range of 0-23.
We must wake up if the dog is barking before 8 or after 22 hours so in that case return true.
In all other cases return false.If the hourOfDay parameter is less than 0 or greater than 23 return false.
*/

package challenges_2_methods;

public class Barking_Dog {

    public static void main(String[] args) {
        System.out.println(shouldWakeUp(true, 1)); // true (лает, 01:00 — ночь)
        System.out.println(shouldWakeUp(false, 2)); // false (не лает)
        System.out.println(shouldWakeUp(true, 8)); // false (лает, но уже не ночь)
        System.out.println(shouldWakeUp(true, 23)); // true (лает, 23:00 — ночь)
        System.out.println(shouldWakeUp(true, -1)); // false (некорректный час)
    }

    public static boolean shouldWakeUp(boolean barking, int hourOfDay) {
        if (hourOfDay < 0 || hourOfDay > 23) {
            return false;
        }
        if (barking && (hourOfDay < 8 || hourOfDay > 22)) {
            return true;
        }
        return false;
    }
}
