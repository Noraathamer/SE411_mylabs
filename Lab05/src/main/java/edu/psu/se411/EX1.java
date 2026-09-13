package edu.psu.se411;

import edu.psu.se411.exceptions.InvalidAgeException;

public class EX1 {
//3
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age is not valid.");
        } else {
            System.out.println("Age valid.");
        }
    }

    public static void main(String[] args) {
    	//4
    	  try {
              validateAge(16);
          } catch (InvalidAgeException e) {
              System.out.println(e.getMessage());
          }
      }
    }
