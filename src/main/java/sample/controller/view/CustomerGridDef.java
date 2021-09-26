package sample.controller.view;


import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomerGridDef  {

	public static String COLUMN_NAMES[] =
		{ "company", "product"};
	public static String COLUMN_DATA_NAMES[] =
		{ "company", "product"};
	public static Integer COLUMN_SIZES[] = { 40, 60};
	public static String TITLE_POPUPS = "Customer";

	public List<String> getColumnNames() {
		return Arrays.asList(COLUMN_NAMES);
	}

	public List<Integer> getColumnSizes() {
		return Arrays.asList(COLUMN_SIZES);
	}

	public List<String> getColumnDataName() {
		return Arrays.asList(COLUMN_DATA_NAMES);
	}

	public Class<?> getCreateView() {
		return MainView.class;
	}

	public String getTitlePopups() {
		return TITLE_POPUPS;
	}

}
