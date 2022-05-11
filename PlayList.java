package playList;

public class PlayList {
    private SongNode head; //to keep track of the start of the playlist
    private SongNode nowPlaying = null; //to keep track of the song currently playing

    public void playList() { //create an empty playlist
        head = null;
    }

    public boolean isEmpty() {
        return head == null; //if head is null then the playlist does not contain any songs
    }

    /**
     * method to add a new song to the end of the playlist or as the first node if the playlist is empty
     * @param s is the name of the song that is being added
     */
    public void addNode(String s) {
        SongNode temp = new SongNode(s); //create a new songnode with the name passed as the argument
        SongNode tracker = head; //variable to find the end of the playlist

        if(this.isEmpty()) { //see if the list is empty and make the song node the first node in the playlist
            temp.setNext(head);
            temp.setPrevious(head);
            head = temp;
            nowPlaying = head;
        } else {
            try {
                while(tracker.getNext() != null) { //find the end of the playlist
                    tracker = tracker.getNext();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            tracker.setNext(temp);
            temp.setPrevious(tracker);
            // nowPlaying = head;
        }
    }

    /**
     * remove a particular song from the playlist
     * @param s is the name of the song that needs to be removed
     */
    public void removeNode(String s) {
        SongNode back = null; //pointer to keep track of the song before the song that is to be removed
        boolean found = false; //boolean to see if the song has been found
        SongNode tracker = head; //pointer to find the song that is to be removed

        if(this.isEmpty()) {
            System.out.println("The playlist is empty!"); //A song cannot be removed from an empty playlist
        } else {
            try {
                while(tracker != null && !found) { 
                    if(tracker.getTitle().equals(s)) {
                        found = true; //the loop ends if the song is found 
                    } else {
                        back = tracker;
                        tracker = tracker.getNext();
                    }
                }

                if(found) {
                    if(back == null && tracker.getNext() == null) { //if the playlist only contains one song 
                        head = null; //practically, the playlist is deleted if the only song is removed
                        nowPlaying = head; //no song is being played since the playlist is empty
                    } else if(back == null) { //if the first is to be removed
                        head = tracker.getNext(); //the start of the playlist is moved one object forward
                        tracker.getNext().setPrevious(back);
                        tracker.setNext(null); //the connection between the song and the playlist is broken and the song is removed
                        tracker.setPrevious(null);
                        nowPlaying = head; //the first song in the playlist is now playing
                    } else if(tracker.getNext() == null) { //if the last song in the playlist is to be removed
                        back.setNext(null); //the next for the previous song is set to null 
                        tracker.setPrevious(null); //the connection between the song and the playlist is broken by setting the previous field to null
                        if(nowPlaying == tracker) {
                        nowPlaying = back; //the previous song is now playing
                        }
                    } else { //if any other song is to be removed
                        back.setNext(tracker.getNext());// the previous song points to the next song
                        tracker.getNext().setPrevious(back);// the next song points to the previous song
                        tracker.setNext(null); //the connection between the removed song and the playlist is broken
                        tracker.setPrevious(null); //the conncetion between the removed song and the playlist is broken
                        if(nowPlaying == tracker) {
                        nowPlaying = back;// the previous song is now playing
                        }
                    }
                    System.out.println(tracker.getTitle() + " has been removed from the playlist.");
                } else {
                    System.out.println("This song does not exist in the playlist."); //if the song to be removed does not exist in the playlist
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * play the current song
     */
    public void playSong() {
        if(nowPlaying != null) {
            System.out.println(nowPlaying.getTitle() + " is playing.");
        } else {
            System.out.println("The list does not contain any songs."); //the nowPlaying pointer points to null if the playlist does not contain any songs
        }
    }

    /**
     * skip to the next song
     */
    public void skipAhead() {
        if(nowPlaying != null) { //if the playlist contains any songs
        if(nowPlaying.getNext() != null) {
            nowPlaying = nowPlaying.getNext(); //skip the song by pointing to the next song
            System.out.println("skipped...");
            System.out.println(nowPlaying.getTitle() + " is now playing.");
        } else {
            System.out.println("You have reached the end of the playlist."); //cannot skip ahead if the end of the playlist is reached
        }
    } else {
        System.out.println("Cannot skip ahead because playlist is empty."); //the nowPlaying pointer points to null if the playlist does not contain any songs
    }
}
    /**
     * skip to the previous song
     */
    public void skipBack() {
        if(nowPlaying != null) { //if the playlist contains any songs
        if(nowPlaying.getPrevious() != null) {
        nowPlaying = nowPlaying.getPrevious(); //skip the song by pointing to the previous song
        System.out.println("skipped...");
        System.out.println(nowPlaying.getTitle() + " is now playing.");
        } else {
            System.out.println("You have reached the start of the list."); //cannot skip back if the start of the playlist is reached
        }
    } else {
        System.out.println("Cannot skip back because playlist is empty."); //the nowPlaying pointer points to null if the playlist does not contain any songs
    }
}

    /**
     * print the playlist by printing the title of the songs
     */
    public void printNodes() {
        SongNode tracker = head;
        if(this.isEmpty()) {
            System.out.println("The playlist does not contain any nodes."); //there are no songs to print from an empty playlist
        } else {
            try {
                while(tracker != null) { //print all songs until the end of the playlist is reached
                    System.out.println(tracker.getTitle());
                    tracker = tracker.getNext();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * search for a particular song in the playlist
     * @param songName is the name of the song that needs to be searched in the playlist
     */
    public void search(String songName) {
        SongNode tracker = head; 
        boolean found = false;
        int index = 1; //variable to find the index of the song in the playlist (BONUS)
        if(this.isEmpty()) {
            System.out.println("The playlist is empty."); //cannot search for a song in an empty playlist
        } else {
            try {
                while(tracker != null && !found) { //loop until the song is found or the end of the playlist is reached
                    if(tracker.getTitle().equals(songName)) {
                        found = true;
                    } else {
                        index++;
                        tracker = tracker.getNext();
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            if(found) {
                System.out.println(songName + " was found in the playlist at index " + index); //let the user know that the song was found and the index it was found at
            } else {
                System.out.println(songName + " was not found in the playlist"); //let the user know that the song was not found in the playlist
            }
        }
    }

    /**
     * prints out the length of the playlist
     */
    public void listLength() {
        SongNode tracker =  head;
        int i = 0;
        if(this.isEmpty()) {
            System.out.println("The playlist does not contain any songs. Length is 0.");
        } else {
            try {
                while(tracker != null) {
                    i++;
                    tracker = tracker.getNext();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            if(i == 1) {
                System.out.println("The playlist contains " + i + " song.");
            } else {
                System.out.println("The playlist contains " + i + " songs.");
            }
        }
    }

    /**
     * print out the memory address of the song objects in the playlist
     */
    public void printObjects() {
        SongNode tracker = head;
        if(this.isEmpty()) {
            System.out.print("The list does not contain any objects."); //cannot print objects from an empty list
        } else {
            try {
                while(tracker != null) { //loop until the end of the list is reached
                    System.out.print(tracker + "\n");
                    tracker = tracker.getNext();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}