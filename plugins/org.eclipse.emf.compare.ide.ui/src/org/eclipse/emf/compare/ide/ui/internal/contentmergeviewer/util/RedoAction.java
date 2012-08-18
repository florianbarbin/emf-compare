package org.eclipse.emf.compare.ide.ui.internal.contentmergeviewer.util;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.compare.ide.ui.internal.util.EMFCompareEditingDomain;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.action.Action;

/**
 * An redo action is implemented by using the {@link org.eclipse.emf.common.command.CommandStack}.
 */
public class RedoAction extends Action {
	protected EMFCompareEditingDomain domain;

	public RedoAction(EMFCompareEditingDomain domain) {
		super(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item", new Object[] {"" })); //$NON-NLS-1$ //$NON-NLS-2$
		this.domain = domain;
		update();
	}

	public RedoAction() {
		super(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item", new Object[] {"" })); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public void run() {
		domain.getCommandStack().redo();
	}

	public void update() {
		setEnabled(domain.getCommandStack().canRedo());

		Command redoCommand = domain.getCommandStack().getRedoCommand();
		if (redoCommand != null && redoCommand.getLabel() != null) {
			setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item", new Object[] {redoCommand //$NON-NLS-1$
					.getLabel() }));
		} else {
			setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item", new Object[] {"" })); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (redoCommand != null && redoCommand.getDescription() != null) {
			setDescription(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item_description", //$NON-NLS-1$
					new Object[] {redoCommand.getDescription() }));
		} else {
			setDescription(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item_simple_description")); //$NON-NLS-1$
		}
	}
}