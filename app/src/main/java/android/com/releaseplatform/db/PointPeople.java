package android.com.releaseplatform.db;

import org.litepal.crud.DataSupport;

public class PointPeople extends DataSupport {

    private int id;
    private String message;
    private String time;

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {

        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }



}
