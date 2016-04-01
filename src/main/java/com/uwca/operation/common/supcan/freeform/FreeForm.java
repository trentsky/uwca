package com.uwca.operation.common.supcan.freeform;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.uwca.operation.common.supcan.common.Common;
import com.uwca.operation.common.supcan.common.properties.Properties;

/**
 * FreeForm
 */
@XStreamAlias("FreeForm")
public class FreeForm extends Common {

	public FreeForm() {
		super();
	}
	
	public FreeForm(Properties properties) {
		this();
		this.properties = properties;
	}
	
}
