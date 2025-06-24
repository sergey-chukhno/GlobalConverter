# Global Converter – Implementation Plan

## Project Requirements

### 1. Functionality
- Create a Java application to convert a user-input string between different numeric bases:
  - hexadecimal
  - octal
  - decimal
  - binary
  - text (ASCII)
- The user must be able to:
  - Input a string (only alphanumeric characters allowed).
  - Choose the target base (with both full and short options: e.g., "hexadecimal" or "-h").
  - The program must validate both the string and the base, prompting again on error.
  - Each character is converted to its ASCII value, then to the chosen base.
  - The result is a concatenated string of the converted values.
  - The conversion must be reversible: e.g., hex → text and text → hex.
- **No use of Java's built-in conversion functions** (must implement conversions manually).

### 2. Encryption
- Add the ability to encrypt/decrypt the string using a simple algorithm (e.g., Caesar cipher).
- The user must be able to specify the encryption key.
- If multiple algorithms are implemented, allow the user to select which one.
- **No use of external libraries or built-in encryption functions.**

### 3. Design
- Good class decomposition (no monolithic code).
- The program should be tested with various examples.

### 4. Command-line Interface
- The program should be runnable from the command line, with options for base and encryption.

---

## Step-by-Step Implementation Plan

### Step 1: Design the Class Structure
- Propose a modular class design:
  - `Converter` (handles base conversions)
  - `Validator` (validates input)
  - `Cipher` (handles encryption/decryption, e.g., CaesarCipher)
  - `Main` (handles user interaction and command-line parsing)
  - (Optional) `Base` enum for base types

### Step 2: Implement Input Validation
- Ensure only alphanumeric strings are accepted.
- Validate base and encryption options.

### Step 3: Implement Manual Base Conversions
- Implement ASCII ↔ base conversions (hex, octal, decimal, binary, text) without using Java's built-in methods.

### Step 4: Implement Reversible Conversion Logic
- Ensure that any conversion can be reversed (e.g., hex → text and text → hex).

### Step 5: Implement Encryption/Decryption
- Implement Caesar cipher (and optionally others) for both encryption and decryption.

### Step 6: Command-Line Interface
- Parse command-line arguments for input string, base, and encryption options.

### Step 7: Testing
- Test with various examples to ensure correctness and reversibility. 