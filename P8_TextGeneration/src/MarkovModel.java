
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Markov Model
// Course: CS 300 Spring 2024
//
// Author: Aditya Goyal
// Email: agoyal33@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.HashMap;

/**
 * A class that represents a Markov model for generating random text based on a sample text. The
 * model uses a sliding window approach to analyze the occurrence of characters following a sequence
 * of characters of length k.
 */
public class MarkovModel {

  private HashMap<String, MyStack<Character>> model; // A map of substrings of length windowWidth to
                                                     // stacks containing the observed characters
                                                     // which follow that substring of characters.

  private MyQueue<Character> currentQueue; // The current windowWidth number of characters that the
                                           // model will use to predict the next character.

  private int windowWidth; // The number of characters to consider in a substring when generating
                           // new text.

  private boolean shuffleStacks; // A boolean value indicating whether to shuffle the stacks during
                                 // text generation.


  /**
   * Constructs a MarkovModel with a specified order. This model will predict the next character in
   * the generated text based on strings of length k.
   * 
   * @param k       - the order of the Markov model (length of substrings to consider).
   * @param shuffle - whether this model should shuffle the stacks during the text generation phase.
   */
  public MarkovModel(int k, boolean shuffle) {
    model = new HashMap<String, MyStack<Character>>();
    currentQueue = new MyQueue<Character>();
    windowWidth = k;
    shuffleStacks = shuffle;
  }

  /**
   * Reads in the provided text and builds a model, which maps each k-length substring of the text
   * to a stack containing all of the characters that follow that substring in the text. (See the
   * writeup for more details.)
   * 
   * @param text - the text to be processed to build the model.
   */
  public void processText(String text) {
    for (int i = 0; i <= text.length() - windowWidth; i++) {

      String substring = text.substring(i, i + windowWidth);

      if (model.get(substring) != null) {
        if (i + windowWidth < text.length()) {
          model.get(substring).push(text.charAt(i + windowWidth));
        }
      }

      else {
        if (i + windowWidth < text.length()) {
          MyStack<Character> newStack = new MyStack<Character>();
          newStack.push(text.charAt(i + windowWidth));
          model.put(substring, newStack);
        }
      }
    }
  }

  /**
   * Initializes the current queue with the first k-letter substring from the text, setting the
   * initial state for text generation.
   * 
   * @param text - the text from which to derive the initial queue state.
   */
  public void initializeQueue(String text) {

    // current queue may not be empty, that is why initialize the queue to the correct one every
    // time.

    MyQueue<Character> que = new MyQueue<Character>();
    if (text.length() >= windowWidth) {
      String substring = text.substring(0, windowWidth);

      for (int j = 0; j < substring.length(); j++) {
        que.enqueue(substring.charAt(j));
      }
    }
    currentQueue = que;
  }

  /**
   * Generates text of a specified length based on the model.
   * 
   * @param length - the desired length of the generated text.
   * @param text   - the text to use for re-seeding the model if necessary.
   * @return the generated text.
   */
  public String generateText(int length, String text) {

    String empty = "";
    empty += currentQueue.toString();

    int i = 0;
    while (empty.length() != length) {
      String key = currentQueue.toString();
      if ((model.get(key) != null) || ((!model.get(key).isEmpty()))) {
        Character add = model.get(key).getList().get(0);
        empty += add;

        if (shuffleStacks) {
          model.get(key).pop(); // we have to remove that character from the stack (the character
                                // that we are adding)
          model.get(key).shuffle(); // its a reference that we are passing, so it does get updated.
        }
        currentQueue.dequeue();
        currentQueue.enqueue(add);
      }

      else {
        // doing process text here wont achieve anything. it will just add duplicate things to the
        // stack of a particular key. this is the case when we are not removing characters from the
        // stack of a particular key of a HashMap. I got to know from TA that we have to remove that
        // character. Therefore, we have to process the text again , so that the character stack for
        // a particular key of HashMap is refilled but there might be duplicates.
        processText(text);
        initializeQueue(text);
        empty += generateText(length, text) + "\n";
      }
    }
    return empty;
  }
}

// generates different iterations so that, that iteration exists, and so that
// the only way the iterations doesn't exist

// if we don't shuffle, it will give the same output every time and the method will be called
// infinitely. The shuffling is very important.
