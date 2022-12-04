package entidad;

public class msg {
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_BLACK = "\u001B[30m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_BLUE = "\u001B[34m";
	private static final String ANSI_PURPLE = "\u001B[35m";
	private static final String ANSI_CYAN = "\u001B[36m";
	private static final String ANSI_WHITE = "\u001B[37m";
	
	public static void success(String msg){
		System.out.println(ANSI_GREEN + msg + ANSI_RESET);
	}
	
	public static void warning(String msg){
		System.out.println(ANSI_YELLOW + msg + ANSI_RESET);
	}
	
	public static void failed(String msg){
		System.out.println(ANSI_RED + msg + ANSI_RESET);
	}
	
	public static void info(String msg){
		System.out.println(ANSI_CYAN + msg + ANSI_RESET);
	}
}