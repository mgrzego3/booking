package booking;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Discount {

    public Discount() {
    }

    @Id
    private String code;
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
