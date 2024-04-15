package cauzy.familytasklist.view;

import java.util.Scanner;

import cauzy.familytasklist.controller.TaskList;

public class Console {
	
	private final Scanner scan = new Scanner(System.in);
	
	private final TaskList taskList;
	
	public Console(TaskList taskList) {
		this.taskList = taskList;
	}
	
	public void startConsole() {
		 boolean exit = false;
	        while (!exit) {
	            System.out.println("\n--- TaskList Management System---");
	            System.out.println("1. Add people in family");
	            System.out.println("2. Remove people of family");
	            System.out.println("3. List members of family");
	            System.out.println("4. Filter members of family by ageGroup");
	            System.out.println("5. Add task in taskList");
	            System.out.println("6. Remove task of tasklist");
	            System.out.println("7. Update task from tasklist");
	            System.out.println("8. List tasks of tasklist");
	            System.out.println("9. Filter task of tasklist by status");
	            System.out.println("10. Filter task of tasklist by tasklevel");
	            System.out.println("11. Filter task of tasklist by member of family");
	            System.out.println("12. Sair");
	            System.out.print("Choose a option: ");
	            int option = scan.nextInt();
	            scan.nextLine(); // Consumir nova linha

	            switch (option) {
	                case 1:
	                    this.adicionarLivroConsole();
	                    break;
	                case 2:
	                    this.adicionarUsuarioConsole();
	                    break;
	                case 3:
	                    this.emprestarLivroConsole();
	                    break;
	                case 4:
	                    this.devolverLivroConsole();
	                    break;
	                case 5:
	                    this.enviarLembretesDevolucao();
	                    break;
	                case 6:
	                    this.listarUsuariosConsole(biblioteca.getUsuarios());
	                    break;
	                case 7:
	                    exit = true;
	                    break;
	                default:
	                    System.out.println("Invalid Option");
	                    break;
	            }
	        }
	        scan.close();
	}
	
}
