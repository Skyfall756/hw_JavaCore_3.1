import java.io.File;
import java.io.FilenameFilter;

public  class MyFileNameFilter implements FilenameFilter {

    private String extension;

    public MyFileNameFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(extension);
    }
}
