import tkinter as tk
from tkinter import messagebox
import random
import string

class PasswordGenerator:
    def __init__(self, root):
        self.root = root
        self.root.title("Password Generator")
        self.root.geometry("400x200")

        self.label_length = tk.Label(root, text="Password Length:")
        self.label_length.pack(pady=10)

        self.length_entry = tk.Entry(root)
        self.length_entry.pack(pady=10)

        self.generate_button = tk.Button(root, text="Generate Password", command=self.generate_password)
        self.generate_button.pack(pady=20)

        self.password_label = tk.Label(root, text="")
        self.password_label.pack(pady=10)

    def generate_password(self):
        try:
            length = int(self.length_entry.get())
            if length <= 0:
                raise ValueError("Password length must be greater than 0")
            
            password = self.generate_random_password(length)
            self.password_label.config(text=f"Generated Password: {password}")
        except ValueError as e:
            messagebox.showerror("Error", str(e))

    def generate_random_password(self, length):
        characters = string.ascii_letters + string.digits + string.punctuation
        password = ''.join(random.choice(characters) for _ in range(length))
        return password

if __name__ == "__main__":
    root = tk.Tk()
    app = PasswordGenerator(root)
    root.mainloop()
