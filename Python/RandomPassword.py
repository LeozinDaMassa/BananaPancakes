import tkinter as tk
from tkinter import messagebox
import random
import string
import pyperclip

class PasswordGenerator:
    def __init__(self, root):
        self.root = root
        self.root.title("Password Generator")
        self.root.geometry("500x300")

        self.label_length = tk.Label(root, text="Password Length:")
        self.label_length.pack(pady=10)

        self.length_entry = tk.Entry(root)
        self.length_entry.pack(pady=10)

        self.checkbox_frame = tk.Frame(root)
        self.checkbox_frame.pack(pady=10)

        self.include_letters_var = tk.IntVar()
        self.include_numbers_var = tk.IntVar()
        self.include_lowercase_var = tk.IntVar()
        self.include_uppercase_var = tk.IntVar()
        self.include_symbols_var = tk.IntVar()

        tk.Checkbutton(self.checkbox_frame, text="Include Letters", variable=self.include_letters_var).grid(row=0, column=0, padx=5)
        tk.Checkbutton(self.checkbox_frame, text="Include Numbers", variable=self.include_numbers_var).grid(row=0, column=1, padx=5)
        tk.Checkbutton(self.checkbox_frame, text="Include Lowercase", variable=self.include_lowercase_var).grid(row=0, column=2, padx=5)
        tk.Checkbutton(self.checkbox_frame, text="Include Uppercase", variable=self.include_uppercase_var).grid(row=0, column=3, padx=5)
        tk.Checkbutton(self.checkbox_frame, text="Include Symbols", variable=self.include_symbols_var).grid(row=0, column=4, padx=5)

        self.generate_button = tk.Button(root, text="Generate Password", command=self.generate_password)
        self.generate_button.pack(pady=20)

        self.password_label = tk.Label(root, text="")
        self.password_label.pack(pady=10)

        self.copy_button = tk.Button(root, text="Copy to Clipboard", command=self.copy_to_clipboard)
        self.copy_button.pack(pady=10)

    def generate_password(self):
        try:
            length = int(self.length_entry.get())
            if length <= 0:
                raise ValueError("Password length must be greater than 0")

            selected_characters = ""
            if self.include_letters_var.get():
                selected_characters += string.ascii_letters
            if self.include_numbers_var.get():
                selected_characters += string.digits
            if self.include_lowercase_var.get():
                selected_characters += string.ascii_lowercase
            if self.include_uppercase_var.get():
                selected_characters += string.ascii_uppercase
            if self.include_symbols_var.get():
                selected_characters += string.punctuation

            if not selected_characters:
                raise ValueError("Select at least one character type")

            password = self.generate_random_password(length, selected_characters)
            self.password_label.config(text=f"Generated Password: {password}")
        except ValueError as e:
            messagebox.showerror("Error", str(e))

    def generate_random_password(self, length, characters):
        password = ''.join(random.choice(characters) for _ in range(length))
        return password

    def copy_to_clipboard(self):
        password = self.password_label.cget("text").replace("Generated Password: ", "")
        pyperclip.copy(password)
        messagebox.showinfo("Password Copied", "Password copied to clipboard!")

if __name__ == "__main__":
    root = tk.Tk()
    app = PasswordGenerator(root)
    root.mainloop()
