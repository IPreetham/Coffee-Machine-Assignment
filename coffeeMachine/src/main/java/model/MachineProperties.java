package model;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedHashMap;

public class MachineProperties{

	@JsonProperty("outlets")
    private OutletInfo outletInfo;

    @JsonProperty("total_items_quantity")
    private LinkedHashMap<String, Integer> ingredientQuantityMap;

    @JsonProperty("beverages")
    private LinkedHashMap<String, LinkedHashMap<String, Integer>> beverages;

	public OutletInfo getOutletInfo() {
		return outletInfo;
	}

	public LinkedHashMap<String, Integer> getIngredientQuantityMap() {
		return ingredientQuantityMap;
	}

	public LinkedHashMap<String, LinkedHashMap<String, Integer>> getBeverages() {
		return beverages;
	}

	public void setOutletInfo(OutletInfo outletInfo) {
		this.outletInfo = outletInfo;
	}

	public void setIngredientQuantityMap(LinkedHashMap<String, Integer> ingredientQuantityMap) {
		this.ingredientQuantityMap = ingredientQuantityMap;
	}

	public void setBeverages(LinkedHashMap<String, LinkedHashMap<String, Integer>> beverages) {
		this.beverages = beverages;
	}
}