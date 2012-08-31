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
package org.openmrs.module.dss;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.chirdlutil.util.Util;


/**
 * Class to save a physician note to disk.
 *
 * @author Steve McKee
 */
public class PhysicianNoteSaver {
	
	private Log log = LogFactory.getLog(this.getClass());
	private Integer patientId;
	private String note;
	
	/**
	 * Constructor method
	 * 
	 * @param patientId ID of the patient the note is being saved for
	 * @param text
	 */
	public PhysicianNoteSaver(Integer patientId, String note) {
		this.patientId = patientId;
		this.note = note;
	}

	/**
	 * Saves the note to disk.
	 */
    public void saveNote() {
		AdministrationService adminService = Context.getAdministrationService();
		String noteDirStr = adminService.getGlobalProperty("dss.physicianNoteDirectory");
		if (noteDirStr == null) {
			log.error("Physician note cannot be saved disk.  The global property dss.physicianNoteDirectory does not " +
					"contain a valid value.");
			return;
		}
		
		File noteDir = new File(noteDirStr);
		if (!noteDir.exists()) {
			log.error("The directory specified for global property dss.physicianNoteDirectory does not exist: " + 
				noteDirStr);
			return;
		}
		
		Patient patient = Context.getPatientService().getPatient(patientId);
		String mrn = patient.getPatientIdentifier().getIdentifier();
		String fileStr = Util.archiveStamp() + "_" + mrn + ".txt";
		File file = new File(noteDir, fileStr);
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
            writer.write(note);
        }
        catch (IOException e) {
            log.error("Error writing Physician note to " + file.getAbsolutePath(), e);
        } finally {
        	if (writer != null) {
        		try {
                    writer.close();
                }
                catch (IOException e) {
                    log.error("Error closing Physician note writer for " + file.getAbsolutePath(), e);
                }
        	}
        }
    }
}
