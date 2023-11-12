package domain;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class UserAdapter extends AbstractTableModel{
	private List<Event> events;  
	private Registered user;
	private String[][] matrix;
	private String[] colNames = { "Event", "Question", "Event Date", "Bet (€)" };

	public UserAdapter(Registered user) {
		this.user = user;
		matrix = new String[getRowCount()][getColumnCount()];
    	int i = 0;

    	System.out.println(getRowCount() + " " + getColumnCount());

    	for(ApustuAnitza aA : user.getApustuAnitzak()) {
    		for(Apustua a: aA.getApustuak()) {
    			matrix[i][0] = a.getKuota().getQuestion().getEvent().getDescription();
    			matrix[i][1] = a.getKuota().getQuestion().getQuestion();
    			matrix[i][2] = aA.getData().toString();
    			matrix[i][3] = aA.getBalioa().toString();
    			i++;
    		}
    	}
	}

    @Override
    public int getRowCount() {
    	int result = 0;
    	for(ApustuAnitza aA : user.getApustuAnitzak()) {
    		for(Apustua a: aA.getApustuak()) {
    			result++;
    		}
    	}
        return result;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {  
        return matrix[rowIndex][columnIndex];
    }

    @Override
        public String getColumnName(int col) {
            return colNames[col];
        }

}