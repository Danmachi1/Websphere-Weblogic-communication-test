"""Compile and test the in-memory permission example, with no network calls."""
import os
from pathlib import Path
import shutil
import subprocess
import tempfile

ROOT = Path(__file__).resolve().parents[1]
JAVA = os.environ.get("JAVA", "java")
JAVAC = shutil.which("javac")
compiler = [JAVAC] if JAVAC else [JAVA, "-m", "jdk.compiler/com.sun.tools.javac.Main"]
with tempfile.TemporaryDirectory(prefix="permission-tests-") as temporary:
    subprocess.run(compiler + ["-d", temporary,
                   str(ROOT / "backend/src/main/java/com/example/filenet/security/SecurityManager.java"),
                   str(ROOT / "tests/SecurityManagerRegressionTest.java")], check=True)
    subprocess.run([JAVA, "-cp", temporary, "SecurityManagerRegressionTest"],
                   cwd=temporary, check=True)
