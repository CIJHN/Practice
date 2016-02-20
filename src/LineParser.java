import java.util.List;

/**
 * A simple interface that parses lines into the Result Type
 *
 * @author ci010
 */
public interface LineParser<Result>
{
	Result parseLine(List<String> line);
}
