import com.acooly.coder.Generator;

/**
 * asdfs
 * <p>
 * Date : 2012-12-12
 *
 * @author zhangpu
 */

public class CodeGeneratorMain {

    public static void main(String[] args) {

        String[] tables = {"dm_customer"};
        Generator.getGenerator().generateTables(tables);

    }

}
