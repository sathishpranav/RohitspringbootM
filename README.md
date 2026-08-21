# RohitspringbootM

A simple Spring Boot HelloWorld application with CI/CD pipeline using Jenkins, SonarQube, Docker, and deployment to AWS (ECR + EKS/ECS).

---

## 📂 Project Structure
- `src/main/java/com/example/helloworld` → Application source code
- `src/main/resources/application.properties` → Spring Boot configuration
- `pom.xml` → Maven build configuration
- `Dockerfile` → Container build instructions
- `Jenkinsfile` → CI/CD pipeline definition
- `deployment.yaml` / `service.yaml` → Kubernetes manifests (for EKS)
- `sonar-project.properties` → SonarQube configuration

---

## 🚀 Build & Run Locally
1. **Clone repo**
   ```bash
   git clone https://github.com/sathishpranav/RohitspringbootM.git
   cd RohitspringbootM
