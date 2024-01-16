# AutomatedWindowManager Readme

## Overview
AutomatedWindowManager is a tool designed to automate window management on Linux systems. It facilitates the organization of windows into different workspaces and provides functionality to save and restore window states.

## Features
- **Workspace Management:** Automatically moves windows to their respective workspaces for better organization.
- **State Persistence:** Remembers the state of windows, allowing users to save and restore configurations.
- **Command-Line Interface:** Utilizes a simple command-line interface for easy interaction.

## Getting Started
To use AutomatedWindowManager, follow these steps:

1. **Compile the Code:**
    - Ensure you have Kotlin installed.
    - Compile the code using the appropriate commands.

2. **Command-Line Arguments:**
    - The tool accepts the following command-line arguments:
        - `--save`: Save the current window states.
        - `--restore`: Restore window states to their saved configurations.
        - `--list`: Display a list of window states.
        - `--printnewwindowidwitholdstate`: Print new window IDs with their old states.

3. **Usage Example:**
   ```bash
   ./AutomatedWindowManager --save
   ```

4. **Help:**
    - To view the available commands and their usage, run:
      ```bash
      ./AutomatedWindowManager --help
      ```

## Examples
### Save Window States
```bash
./AutomatedWindowManager --save
```

### Restore Window States
```bash
./AutomatedWindowManager --restore
```

### List Window States
```bash
./AutomatedWindowManager --list
```

### Print New Window IDs with Old State
```bash
./AutomatedWindowManager --printnewwindowidwitholdstate
```

## Version
AutomatedWindowManager 1.0

## Contributors
- [Yash Verma]

## License
This project is licensed under the [GNU General Public License v3.0] - see the [LICENSE](./LICENSE) file for details.