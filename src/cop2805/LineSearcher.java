package cop2805;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class LineSearcher {

    public List<String> lines = new ArrayList<String>();



    public LineSearcher(String file)
    {
            Path filePath = Path.of(file);
            Charset charset = StandardCharsets.UTF_8;

            try
            {
                lines = Files.readAllLines(filePath, charset);
            }
            catch (FileNotFoundException e)
            {
            throw new RuntimeException(e);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
    }

    public List<String> LineSearcher(int num)
    {
        int sub2 = num - 2;
        int sub1 = num - 1;
        int add1 = num + 1;
        int add2 = num + 2;
        List<String> returnLines = new ArrayList<String>() ;

        if(sub2>=0){returnLines.add(lines.get(sub2));}
        if(sub1>=0){returnLines.add(lines.get(sub1));}
        returnLines.add(lines.get(num));
        if(add1< lines.size()){returnLines.add(lines.get(add1));}
        if(add2< lines.size()){returnLines.add(lines.get(add2));}

        return returnLines;
    }

}
