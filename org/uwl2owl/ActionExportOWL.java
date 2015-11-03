// $Id: eclipse-argo-codetemplates.xml 11347 2006-10-26 22:37:44Z linus $
// Copyright (c) 2015 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies. This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason. IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.uwl2owl;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import org.argouml.configuration.Configuration;
import org.argouml.i18n.Translator;
import org.argouml.persistence.PersistenceManager;
import org.argouml.persistence.ProjectFileView;
import org.argouml.ui.ProjectBrowser;
import org.argouml.util.ArgoFrame;

public class ActionExportOWL extends AbstractAction {

    
    public ActionExportOWL() {
        super("Export OWL...");
    }
    
    public void actionPerformed(ActionEvent e) {
        // TODO: Auto-generated method stub
        PersistenceManager pm = PersistenceManager.getInstance();
        // show a chooser dialog for the file name, only xmi is allowed
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(Translator.localize(
                                       "action.export-project-as-xmi"));
        chooser.setFileView(ProjectFileView.getInstance());
        chooser.setApproveButtonText(Translator.localize(
                                             "filechooser.export"));
        chooser.setAcceptAllFileFilterUsed(true);
        pm.setXmiFileChooserFilter(chooser);

//        chooser.setAcceptAllFileFilterUsed(false);
        
        String fn =Configuration.getString(PersistenceManager.KEY_PROJECT_NAME_PATH);
        if (fn.length() > 0) {
            fn = PersistenceManager.getInstance().getBaseName(fn);
            chooser.setSelectedFile(new File(fn));
        }
        File theFile = null;
        int result = chooser.showSaveDialog(ArgoFrame.getFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
            theFile = chooser.getSelectedFile();
            if (theFile != null) {
                String name = theFile.getName();
                Configuration.setString(
                        PersistenceManager.KEY_PROJECT_NAME_PATH,
                        PersistenceManager.getInstance().getBaseName(
                                theFile.getPath()));
                name = pm.fixXmiExtension(name);
                theFile = new File(theFile.getParent(), name);
                ProjectBrowser.getInstance().trySaveWithProgressMonitor(
                        false, theFile, false);
            }

            
        }
    }

}
