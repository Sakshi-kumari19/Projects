import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Online_Voting_System {
    private static JFrame loginFrame;
    private static JTextField voterIdField;
    private static JPasswordField passwordField;

    private static Map<String, Voter> voters = new HashMap<>();
    private static Map<Integer, Candidate> candidates = new HashMap<>();
    private static Map<Integer, Integer> votes = new HashMap<>();
    private static Set<String> votedVoters = new HashSet<>();
    private static int totalVoters = 3;

    // Define fonts
    private static final Font LARGE_FONT = new Font("Arial", Font.PLAIN, 16);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);

    public static void main(String[] args) {
        
        initializeData();

        //Login frame
        loginFrame = new JFrame("Voter Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 250); 

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        loginFrame.add(panel);

        placeLoginComponents(panel);

        loginFrame.setVisible(true);
    }

    private static void initializeData() {
        //voters and their password data
        voters.put("voter123", new Voter("voter123", "password1", "Sakshi"));
        voters.put("voter234", new Voter("voter234", "password2", "Anil"));
        voters.put("voter345", new Voter("voter345", "password3", "Tina"));

        // Candidate name
        candidates.put(1, new Candidate("BJP"));
        candidates.put(2, new Candidate("Congress"));
        candidates.put(3, new Candidate("AAP"));

        
        for (Integer candidateId : candidates.keySet()) {
            votes.put(candidateId, 0);
        }
    }

    private static void placeLoginComponents(JPanel panel) {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        
        JLabel titleLabel = new JLabel("Online Voting System",SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(titleLabel, gbc);

        
        JLabel userLabel = new JLabel("Voter ID:");
        userLabel.setFont(LARGE_FONT); // Set font size
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(userLabel, gbc);

        voterIdField = new JTextField(20);
        voterIdField.setFont(LARGE_FONT);
        gbc.gridx = 1;
        loginPanel.add(voterIdField, gbc);

        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(LARGE_FONT);
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(LARGE_FONT);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        
        JButton loginButton = new JButton("Login");
        loginButton.setFont(LARGE_FONT);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String voterId = voterIdField.getText();
                String password = new String(passwordField.getPassword());
                if (authenticateVoter(voterId, password)) {
                    Voter voter = voters.get(voterId);
                    if (votedVoters.contains(voterId)) {
                        JOptionPane.showMessageDialog(null, "You have already voted.");
                    } else {
                        showVotingWindow(voter);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid voter ID or password");
                }
            }
        });

        panel.add(loginPanel, BorderLayout.CENTER);
    }

    private static boolean authenticateVoter(String voterId, String password) {
        return voters.containsKey(voterId) && voters.get(voterId).getPassword().equals(password);
    }

    private static void showVotingWindow(Voter voter) {
        loginFrame.dispose();
        JFrame votingFrame = new JFrame("Voting");
        votingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        votingFrame.setSize(500, 400);

        JPanel votingPanel = new JPanel();
        votingPanel.setLayout(new BorderLayout());
        votingFrame.add(votingPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel welcomeLabel = new JLabel("Welcome, " + voter.getName() + "! Please select a candidate to vote:");
        welcomeLabel.setFont(LARGE_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(welcomeLabel, gbc);

        for (Map.Entry<Integer, Candidate> entry : candidates.entrySet()) {
            int candidateId = entry.getKey();
            Candidate candidate = entry.getValue();

            JButton candidateButton = new JButton(candidate.getName());
            candidateButton.setFont(LARGE_FONT);
            candidateButton.setPreferredSize(new Dimension(200, 40));
            gbc.gridy++;
            contentPanel.add(candidateButton, gbc);

            candidateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    votes.put(candidateId, votes.get(candidateId) + 1);
                    votedVoters.add(voter.getId());
                    if (votedVoters.size() >= totalVoters) {
                        showResults();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thank you for voting!");
                        votingFrame.dispose();
                        loginFrame.setVisible(true);
                    }
                }
            });
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        votingPanel.add(scrollPane, BorderLayout.CENTER);

        votingFrame.setVisible(true);
    }

    private static void showResults() {
        JFrame resultFrame = new JFrame("Results");
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultFrame.setSize(500, 400);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel resultLabel = new JLabel("Election Results:");
        resultLabel.setFont(LARGE_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        resultPanel.add(resultLabel, gbc);

        int maxVotes = -1;
        int winningCandidateId = -1;

        for (Map.Entry<Integer, Integer> entry : votes.entrySet()) {
            int candidateId = entry.getKey();
            int voteCount = entry.getValue();
            JLabel candidateLabel = new JLabel(candidates.get(candidateId).getName() + ": " + voteCount + " votes");
            candidateLabel.setFont(LARGE_FONT);
            gbc.gridy++;
            resultPanel.add(candidateLabel, gbc);
            if (voteCount > maxVotes) {
                maxVotes = voteCount;
                winningCandidateId = candidateId;
            }
        }

        if (winningCandidateId != -1) {
            String winnerName = candidates.get(winningCandidateId).getName();
            JLabel winnerLabel = new JLabel("<html>Winner: <b>" + winnerName + "</b></html>");
            winnerLabel.setFont(LARGE_FONT);
            gbc.gridy++;
            resultPanel.add(winnerLabel, gbc);
        }

        JScrollPane scrollPane = new JScrollPane(resultPanel);
        resultFrame.add(scrollPane);

        resultFrame.setVisible(true);
    }

    static class Voter {
        private String id;
        private String password;
        private String name;

        public Voter(String id, String password, String name) {
            this.id = id;
            this.password = password;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }
    }

    static class Candidate {
        private String name;

        public Candidate(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
