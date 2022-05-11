package playList;

import java.util.Scanner;

public class Main {

    public static Scanner keyb = new Scanner(System.in);

    public static void main(String[] args) {

        PlayList newPlayList = new PlayList();
        int menuChoice = 0;
        String songName = "";
        boolean inputType = true;
        String sortOrder = "";

        do {
            System.out.println("\nWelcome to the Song Manager");
            System.out.println("-----------------------------------------------");
            System.out.println("1. Add a song. \t 2. Remove a song.");
            System.out.println("3. Current track. \t 4. Skip ahead.");
            System.out.println("5. Skip back. \t\t 6.  Print track list.");
            System.out.println("7. Search list. \t 8.  Number of tracks. ");
            System.out.println("9. Quit.");
            System.out.println("-----------------------------------------------");
            System.out.println("");

            while (inputType) {
                if (keyb.hasNextInt()) {
                    menuChoice = keyb.nextInt();
                } else {
                    keyb.next();
                    continue;
                }
                inputType = false;
                keyb.nextLine();
            }

            switch (menuChoice) {

            case 1:
                System.out.println("Enter the name of the song:");
                songName = keyb.nextLine();
                newPlayList.addNode(songName);
                break;
            case 2:
                System.out.println("Enter the name of the song:");
                songName = keyb.nextLine();
                newPlayList.removeNode(songName);
                break;
            case 3:
                newPlayList.playSong();
                break;
            case 4:
                newPlayList.skipAhead();
                break;
            case 5:
                newPlayList.skipBack();
                break;
            case 6:
                newPlayList.printNodes();
                break;
            case 7:
                System.out.println("Enter the name of the song:");
                songName = keyb.nextLine();
                newPlayList.search(songName);
                break;
            case 8:
                newPlayList.listLength();
                break;

            case 12:
                newPlayList.printObjects();
                break;

            }
            inputType = true;
        } while (menuChoice != 9);

    }
}
