
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "videos")
@Data@NoArgsConstructor@AllArgsConstructor
public class Video implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long id;
    private String name;
    private String url;
    private String describe;
    private String created;
    private String creator;
    private String modified;
    private String modifier;
    private Integer status;
}
