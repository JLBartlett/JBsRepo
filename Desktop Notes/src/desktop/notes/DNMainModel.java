package desktop.notes;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Model handles the data in the application
 * @author John
 */
public class DNMainModel {

    private Map<String, Font> nameToFontMap;
    private Map<String, Path> nameToFileMap;
    private StringBuilder noteStrBuilder;
    private Path currentFile;
    private String selectedFontName;
    private int selectedFontSize;

    /**
     * Populate fonts and files 
     */
    public DNMainModel() {
        populateFonts();
        populateFiles();
    }
    
    /**
     *
     * @return The currently selected font name
     */
    public String getSelFont() {
        return this.selectedFontName;
    }
    
    /**
     * 
     * @return The currently selected Font
     */
    public Font getFont() {
        return nameToFontMap.get(this.selectedFontName);
    }
    
    /**
     * 
     * @param selFontName The currently selected font name
     */
    public void setSelFont(String selFontName) {
        this.selectedFontName = selFontName;
    }
    
    /**
     * 
     * @return The currently selected font size
     */
    public int getFontSize() {
        return this.selectedFontSize;
    }
    
    /**
     * 
     * @param fontSize  The currently selected font size
     */
    public void setFontSize(int fontSize) {
        this.selectedFontSize = fontSize;
    }
    
    /**
     * 
     * @return The currently selected file name
     */
    public String getCurrentFilename() {
        return currentFile.getFileName().toString();
    }
    
    /**
     * 
     * @return String representing the desktop note
     */
    public String getDesktopNote() {
        return noteStrBuilder.toString();
    }

    /**
     * Create a new note on disk and associated data structures
     * @param name The file name to create
     */
    public void createNewNote(String name) {
        currentFile = Paths.get(getDNDir().toString(), name);
        if (!Files.exists(currentFile)) {
            try {
                Files.createFile(currentFile);
                nameToFileMap.put(name, currentFile);
                noteStrBuilder = new StringBuilder("");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    /**
     * Save the current note to disk
     * @param str The note to save to disk
     */
    public void saveNote(String str) {

        try (BufferedWriter br = Files.newBufferedWriter(currentFile)) {
            List<String> lineList = Arrays.asList(str.split("\n"));
            for (String line : lineList) {
                br.write(line);
                br.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Get all the file names
     * @return an array of all the file names
     */
    public String[] getFileNames() {
        return nameToFileMap.keySet().toArray(new String[0]);
    }
    
    /**
     * Populate the map of file names to actual File's
     */
    private void populateFiles() {
        try {
            Path dnDir = getDNDir();
            if (!Files.exists(dnDir)) {
                createFirstNoteOnDisk();
            } else if (nameToFileMap != null && nameToFileMap.isEmpty()) {
                getLastNoteFromMap();
            } else {
                nameToFileMap = Files.list(dnDir).collect(LinkedHashMap<String, Path>::new,
                        (map, path) -> map.put(path.getFileName().toString(), path),
                        (map1, map2) -> map1.putAll(map2));
                getLastNoteFromMap();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Get the actual desktop note from selected notes file name
     * @param selNote The selected note to select from file Map
     * @return StringBuilder representing the desktop note
     */
    public StringBuilder getSelNoteFromMap(String selNote) {
        try {
        currentFile = nameToFileMap.get(selNote);
        return noteStrBuilder = Files.lines(currentFile).collect(StringBuilder::new,
                (stbu, line) -> stbu.append(String.format("%s\n", line)),
                StringBuilder::append);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return new StringBuilder();
        }
    }

    /**
     * Get the last desktop note from the file Map
     * @throws IOException representing an input/output error
     */
    private void getLastNoteFromMap() throws IOException {
        List<String> fileList = new ArrayList<>(nameToFileMap.keySet());
        String last = fileList.get(fileList.size() - 1);
        currentFile = nameToFileMap.get(last);
        noteStrBuilder = Files.lines(currentFile).collect(StringBuilder::new,
                (stbu, line) -> stbu.append(String.format("%s\n", line)),
                StringBuilder::append);
    }

    /**
     * Creates the first note on disk when application first started.
     * @throws IOException representing an input/output error
     */
    private void createFirstNoteOnDisk() throws IOException {
        Path dnDir = getDNDir();
        Files.createDirectory(dnDir);
        String strFirst = "1_NOTE.TXT";
        currentFile = Paths.get(dnDir.toString(), strFirst);
        Files.createFile(currentFile);
        nameToFileMap = new LinkedHashMap<>();
        nameToFileMap.put(strFirst, currentFile);
        noteStrBuilder = new StringBuilder("");
    }

    /**
     * Get a MS Windows directory for this application
     * (e.g., c:\Users\<user.home>\Desktop\DN_DIR)
     * @return Path representing the desktop note directory
     */
    private Path getDNDir() {
        return Paths.get(System.getProperty("user.home"),
                "Desktop", "DN_DIR");
    }

    /**
     * Populate the font name to actual Font Map from system fonts
     */
    private void populateFonts() {
        nameToFontMap = Arrays.asList(
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts())
                .stream().collect(LinkedHashMap<String, Font>::new,
                        (map, font) -> map.put(font.getName(), font),
                        (map1, map2) -> map1.putAll(map2));
        selectedFontName = nameToFontMap.get("Segoe UI").getName();
        selectedFontSize = 14;
    }

    /**
     * Get all the font names from keys of Font map
     * @return array of all font names
     */
    public String[] getFontNames() {
        return nameToFontMap.keySet().toArray(new String[0]);
    }

    /**
     * Get the default application font
     * @return the default application font
     */
    public Font getDefaultFont() {
        return nameToFontMap.get("Segoe UI");
    }

    /**
     * get the actual Font from the selected font name selected
     * @param selected name of the selected font
     * @return The actual Font from the font Map
     */
    public Font selectFont(String selected) {
        return nameToFontMap.get(selected);
    }
}
