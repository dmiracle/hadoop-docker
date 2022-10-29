package org.hw2q2;

import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;
import java.io.DataInput;
import java.io.DataOutput;

public class CompositeKey implements WritableComparable<CompositeKey> {
    private String title;
    private String userId;
    private String itemId;
    private String rating;
    private char sex;

    public CompositeKey() {
        this.title = "";
        this.userId = "";
        this.itemId = "";
        this.rating = "";
        this.sex = ' ';
    }

    public CompositeKey(String userId, char sex) {
        this.title = "";
        this.userId = userId;
        this.itemId = "";
        this.rating = "";
        this.sex = sex;
    }

    public CompositeKey(String itemId, String rating) {
        this.title = "";
        this.itemId = itemId;
        this.rating = rating;
        this.userId = "";
        this.sex = ' ';
    }

    public CompositeKey(String itemId, String title, Boolean isTitle) {
        this.title = title;
        this.itemId = itemId;
        this.userId = "";
        this.rating = "";
        this.sex = ' ';
    }

    public CompositeKey(String userId, String itemId, String rating) {
        this.title = "";
        this.userId = userId;
        this.itemId = itemId;
        this.rating = rating;
        this.sex = ' ';
    }

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getRating() {
        return rating;
    }

    public char getSex() {
        return sex;
    }

    public void write(DataOutput out) throws IOException {
        WritableUtils.writeString(out, title);
        WritableUtils.writeString(out, userId);
        WritableUtils.writeString(out, itemId);
        WritableUtils.writeString(out, rating);
        out.writeChar(sex);
    }

    public void readFields(DataInput in) throws IOException {
        title = WritableUtils.readString(in);
        userId = WritableUtils.readString(in);
        itemId = WritableUtils.readString(in);
        rating = WritableUtils.readString(in);
        sex = in.readChar();
    }

    public int compareTo(CompositeKey o) {
        if (userId != "") {
            int result = userId.compareTo(o.userId);
            if (result != 0) {
                return result;
            }
        } else if (itemId != "") {
            int result = itemId.compareTo(o.itemId);
            if (result != 0) {
                return result;
            }
        } else if (title != "") {
            int result = title.compareTo(o.title);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return title + "," + itemId + "," + rating;
    }
}
