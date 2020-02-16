public class Event {
    private long id;
    private String name;
    private String startTime;
    private String eventDate;
    private String lastTimeToReg;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", lastTimeToReg='" + lastTimeToReg + '\'' +
                '}';
    }
}
