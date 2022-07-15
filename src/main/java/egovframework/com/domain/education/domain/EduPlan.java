package egovframework.com.domain.education.domain;

import java.util.List;
import lombok.Data;

@Data
public class EduPlan {
    private EduClass eduClass;
    private List<EduCours> eduCours;
}
