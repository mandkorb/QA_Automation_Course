package api.models.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceListResponse {
    private int page;
    @JsonProperty("per_page")
    private int perPage;
    private List<ResourceData> data;

    public boolean areYearsASCSorted() {
        List<Integer> years = data.stream()
                .map(resource -> Integer.parseInt(resource.getYear()))
                .toList();
        List<Integer> yearsSorted = years.stream().sorted().toList();
        return yearsSorted.equals(years);
    }
}
