
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthenticationManager authManager = new AuthenticationManager();
        User currentUser = null;
        PostService postService = new PostService();

        while (true) {
            System.out.println("Welcome to Mini Social Media!");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Sign In
                    do {
                        System.out.print("Enter username: ");
                        String signInUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String signInPassword = scanner.nextLine();

                        if (authManager.authenticate(signInUsername, signInPassword)) {
                            System.out.println("Sign In successful!");
                            currentUser = new User(signInUsername, signInPassword);

                            showUserMenu(currentUser, postService);
                            break;
                        } else {
                            System.out.println("Sign In failed. Incorrect username or password. Try again.");
                        }
                    } while (true);
                    break;

                case 2:
                    // Sign Up
                    do {
                        System.out.print("Enter new username: ");
                        String signUpUsername = scanner.nextLine();
                        System.out.print("Enter new password: ");
                        String signUpPassword = scanner.nextLine();

                        if (authManager.createUser(signUpUsername, signUpPassword)) {
                            System.out.println("Sign Up successful! You can now sign in.");
                            // Automatically sign in the user after successful sign up
                            currentUser = new User(signUpUsername, signUpPassword);
                            showUserMenu(currentUser, postService);
                            break;
                        } else {
                            System.out.println("Sign Up failed. Username already exists. Try again.");
                        }
                    } while (true);
                    break;

                case 3:
                    // Exit
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static void showUserMenu(User user, PostService postService) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome, " + user.getUsername() + "!");
            System.out.println("1. Create Post");
            System.out.println("2. View Posts");
            System.out.println("3. Delete Post");
            System.out.println("4. Sign Out");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {

                case 1:
                    // Create Post
                    System.out.print("Enter post content: ");
                    String postContent = scanner.nextLine();
                    postService.createPost(user, postContent);
                    System.out.println("Post created successfully!");
                    break;

                case 2:
                    // View Posts
                    Post[] userPosts = postService.getPosts();
                    System.out.println("Your Posts:");
                    for (Post post : userPosts) {
                        if (post != null) {
                            System.out.println("Post ID: " + post.getPostId() + ", Content: " + post.getContent());
                        }
                    }
                    break;

                case 3:
                    // Delete Post
                    System.out.print("Enter the ID of the post you want to delete: ");
                    int postIdToDelete = scanner.nextInt();
                    postService.deletePost(user, postIdToDelete);
                    break;


                case 4:
                    // Sign Out
                    System.out.println("Signing out. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }
}
