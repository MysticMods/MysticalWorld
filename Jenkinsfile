#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                echo 'Cleaning Project'
                sh 'chmod +x gradlew'
                sh './gradlew clean'
            }
        }
        stage('Generating Resources') {
            steps {
                echo 'Generating Resources'
                sh './gradlew runData'
            }
        }
        stage('Build and Deploy') {
            steps {
                echo 'Building and Deploying to Maven'
                sh './gradlew build publish'
            }
        }
    }
    post {
        always {
            archive 'build/libs/**.jar'
        }
    }
} 
