package encountergen2.data;

import org.apache.commons.lang3.StringUtils;

public enum DataType {
	CREATURES, INTERESTS, TREASURES, ENCOUNTERS;
	public static boolean isValid(String type) {
		if (type != null) {
			for (DataType datatype : DataType.values()) {
				 if (StringUtils.equalsIgnoreCase(type, datatype.toString())) {
					return true;
				}
			}
		}
		return false;
	}
}
