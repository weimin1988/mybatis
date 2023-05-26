package cloud.yiyefu.mybatis;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CodeAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        PsiDirectory directory = e.getRequiredData(LangDataKeys.IDE_VIEW).getOrChooseDirectory();
        assert directory != null;
        String projectPath = directory.getVirtualFile().getPath();

        CodeDialog codeDialog=new CodeDialog(e.getProject(),true,"code by table");
        codeDialog.show();


    }
    public  static class TestDialog extends DialogWrapper{
        private String result;

        protected TestDialog(@Nullable Project project, boolean canBeParent,String result) {
            super(project, canBeParent);
            this.result=result;
            init();
            setSize(200,60);
        }

        @Override
        protected @Nullable JComponent createCenterPanel() {
            JPanel panel=new JPanel();
            JLabel label=new JLabel();
            label.setText(result);
            panel.add(label);
            return panel;
        }
        @Override
        protected JComponent createSouthPanel() {
            JPanel panel=new JPanel();
            JButton button=new JButton();
            button.setText("ok");
            button.addActionListener(e -> {

                dispose();
                close(0);
            });
            panel.add(button);
            return  button;
        }
    }

}
