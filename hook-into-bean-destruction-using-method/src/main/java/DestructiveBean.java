import org.springframework.beans.factory.InitializingBean;

import java.io.File;

public class DestructiveBean implements InitializingBean {
    private File file;
    private String filePath;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("initializing bean");

        if (filePath == null) {
            throw new IllegalArgumentException("You must specify the filePath property of " + DestructiveBean.class);
        }

        this.file = new File(filePath);
        this.file.createNewFile();

        System.out.println("File exists: " + file.exists());
    }

    public void destroy() {
        System.out.println("destroying bean");

        if (!file.delete()) {
            System.err.println("Error: failed to delete file");
        }

        System.out.println("File exists: " + file.exists());
    }
}
