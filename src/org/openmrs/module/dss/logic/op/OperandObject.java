/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.dss.logic.op;

import org.openmrs.logic.op.ComparisonOperator;
import org.openmrs.logic.op.Operand;

/**
 * @see Operand
 */
public class OperandObject implements Operand {
	
	private Object object;
	
	/**
	 * Constructor to create this Operand with the String backing object. A pointer to the String is
	 * kept.
	 * 
	 * @param string the backing String
	 */
	public OperandObject(Object object) {
		this.object = object;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return object == null ? "null" : object.toString();
	}
	
	/**
	 * @see org.openmrs.logic.op.Operand#supports(org.openmrs.logic.op.ComparisonOperator)
	 */
	public boolean supports(ComparisonOperator operator) {
		return (ComparisonOperator.EQUALS.equals(operator));
	}
	
	/**
	 * @return The Object stored by this class.
	 */
	public Object getObject() {
		return object;
	}
	
}
