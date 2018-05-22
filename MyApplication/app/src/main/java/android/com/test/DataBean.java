package android.com.test;

/**
 * Created by Administrator on 2018/5/22.
 */

public class DataBean {
    private String autor;
    private String content;

    public DataBean(String autor, String content) {
        this.autor = autor;
        this.content = content;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
