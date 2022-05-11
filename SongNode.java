package playList;

public class SongNode {
    private String title; //to store the title of the song
    private SongNode next; //to store the reference to the next song in the playlist
    private SongNode previous; //to store the reference to the previous song in the playlist

    /**
     * create a new SongNode 
     * @param s is the title of the song
     */
    SongNode(String s) { 
        this.setTitle(s); //create the songNode object using the name passed as the argument 
        next = null;
        previous = null;
    }

    public SongNode getNext() {
        return this.next;
    }

    public void setNext(SongNode next) {
        this.next = next;
    }

    public SongNode getPrevious() {
        return this.previous;
    }

    public void setPrevious(SongNode previous) {
        this.previous = previous;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String s) {
        this.title = s;
    }
}
