# Startup Survey API Documentation

## Overview
The Startup Survey API provides comprehensive CRUD operations for managing startup survey submissions with draft mode support, allowing users to save partial submissions and update them multiple times before final submission.

## Base URL
```
http://localhost:8080/api/v1/startup-surveys
```

## API Endpoints

### 1. Save Survey as Draft
**Endpoint:** `POST /api/v1/startup-surveys/draft`

**Description:** Save a new startup survey as draft or update an existing draft. Supports partial submissions.

**Request Body:**
```json
{
  "startupName": "Tech Startup XYZ",
  "founderName": "John Doe",
  "genderOfPrimaryFounder": "MALE",
  "ageOfPrimaryFounder": 28,
  "email": "john@example.com",
  "phone": "9876543210",
  "city": "Bangalore",
  "district": "Bangalore",
  "state": "Karnataka"
}
```

**Response (Success - 201):**
```json
{
  "success": true,
  "message": "Survey saved as draft successfully",
  "statusCode": 201,
  "data": {
    "id": 1,
    "startupName": "Tech Startup XYZ",
    "founderName": "John Doe",
    "createdAt": "2024-06-02T10:30:00",
    "updatedAt": "2024-06-02T10:30:00"
  }
}
```

---

### 2. Submit Survey
**Endpoint:** `POST /api/v1/startup-surveys/submit`

**Description:** Submit a complete startup survey for final processing.

**Request Body:**
```json
{
  "id": 1,
  "startupName": "Tech Startup XYZ",
  "founderName": "John Doe",
  "email": "john@example.com",
  "businessMentoringTypes": ["BUSINESS_MODEL_REFINEMENT", "PRICING_STRATEGY"],
  "startupStage": "MVP",
  "revenueModel": "SUBSCRIPTION",
  "productReadinessLevel": "LAUNCHED"
}
```

**Response (Success - 201):**
```json
{
  "success": true,
  "message": "Survey submitted successfully",
  "statusCode": 201,
  "data": {
    "id": 1,
    "startupName": "Tech Startup XYZ",
    "createdAt": "2024-06-02T10:30:00",
    "updatedAt": "2024-06-02T11:45:00"
  }
}
```

---

### 3. Get Survey by ID
**Endpoint:** `GET /api/v1/startup-surveys/{id}`

**Description:** Retrieve a specific survey by its ID.

**Path Parameters:**
- `id` (Long) - Survey ID

**Example Request:**
```
GET /api/v1/startup-surveys/1
```

**Response (Success - 200):**
```json
{
  "success": true,
  "message": "Survey retrieved successfully",
  "statusCode": 200,
  "data": {
    "id": 1,
    "startupName": "Tech Startup XYZ",
    "founderName": "John Doe",
    "email": "john@example.com",
    "businessMentoringTypes": ["BUSINESS_MODEL_REFINEMENT", "PRICING_STRATEGY"],
    "keyTeamMembers": [
      {
        "id": 1,
        "name": "John Doe",
        "designation": "Founder & CEO",
        "keyTasks": "Strategy and Vision"
      }
    ]
  }
}
```

---

### 4. Get All Surveys with Pagination
**Endpoint:** `GET /api/v1/startup-surveys`

**Description:** Retrieve all surveys with pagination and sorting support.

**Query Parameters:**
- `page` (int, default: 0) - Page number (0-indexed)
- `size` (int, default: 20) - Number of records per page
- `sort` (String) - Sorting criteria (e.g., `createdAt,desc` or `startupName,asc`)

**Example Request:**
```
GET /api/v1/startup-surveys?page=0&size=10&sort=createdAt,desc
```

**Response (Success - 200):**
```json
{
  "success": true,
  "message": "Surveys retrieved successfully",
  "statusCode": 200,
  "data": {
    "content": [
      {
        "id": 1,
        "startupName": "Tech Startup XYZ",
        "email": "john@example.com",
        "createdAt": "2024-06-02T10:30:00"
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 10,
      "totalElements": 50
    }
  }
}
```

---

### 5. Get Survey by Email
**Endpoint:** `GET /api/v1/startup-surveys/search/email`

**Description:** Retrieve a survey by founder's email address.

**Query Parameters:**
- `email` (String) - Email address

**Example Request:**
```
GET /api/v1/startup-surveys/search/email?email=john@example.com
```

**Response (Success - 200):**
```json
{
  "success": true,
  "message": "Survey retrieved successfully",
  "statusCode": 200,
  "data": {
    "id": 1,
    "email": "john@example.com",
    "startupName": "Tech Startup XYZ"
  }
}
```

---

### 6. Get Survey by Startup Name
**Endpoint:** `GET /api/v1/startup-surveys/search/name`

**Description:** Retrieve a survey by startup name.

**Query Parameters:**
- `name` (String) - Startup name

**Example Request:**
```
GET /api/v1/startup-surveys/search/name?name=Tech%20Startup%20XYZ
```

**Response (Success - 200):**
```json
{
  "success": true,
  "message": "Survey retrieved successfully",
  "statusCode": 200,
  "data": {
    "id": 1,
    "startupName": "Tech Startup XYZ",
    "email": "john@example.com"
  }
}
```

---

### 7. Update Survey (Full Update)
**Endpoint:** `PUT /api/v1/startup-surveys/{id}`

**Description:** Fully update an existing survey. All fields must be provided.

**Path Parameters:**
- `id` (Long) - Survey ID

**Request Body:**
```json
{
  "startupName": "Tech Startup XYZ Updated",
  "founderName": "John Doe",
  "email": "john@example.com",
  "totalTeamSize": 15,
  "fullTimeEmployees": 12,
  "partTimeEmployees": 3
}
```

**Response (Success - 200):**
```json
{
  "success": true,
  "message": "Survey updated successfully",
  "statusCode": 200,
  "data": {
    "id": 1,
    "startupName": "Tech Startup XYZ Updated",
    "updatedAt": "2024-06-02T12:00:00"
  }
}
```

---

### 8. Partial Update (Draft Mode)
**Endpoint:** `PATCH /api/v1/startup-surveys/{id}`

**Description:** Partially update a survey. Only provided fields will be updated.

**Path Parameters:**
- `id` (Long) - Survey ID

**Request Body:**
```json
{
  "totalTeamSize": 20,
  "businessModelType": "B2B"
}
```

**Response (Success - 200):**
```json
{
  "success": true,
  "message": "Survey updated successfully",
  "statusCode": 200,
  "data": {
    "id": 1,
    "totalTeamSize": 20,
    "businessModelType": "B2B",
    "updatedAt": "2024-06-02T12:15:00"
  }
}
```

---

### 9. Delete Survey
**Endpoint:** `DELETE /api/v1/startup-surveys/{id}`

**Description:** Delete a survey by ID.

**Path Parameters:**
- `id` (Long) - Survey ID

**Example Request:**
```
DELETE /api/v1/startup-surveys/1
```

**Response (Success - 200):**
```json
{
  "success": true,
  "message": "Survey deleted successfully",
  "statusCode": 200,
  "data": null
}
```

---

## Error Responses

### Not Found (404)
```json
{
  "success": false,
  "message": "Startup Survey not found with id: 999",
  "statusCode": 404,
  "data": null
}
```

### Bad Request (400)
```json
{
  "success": false,
  "message": "Failed to save survey as draft: Invalid input",
  "statusCode": 400,
  "data": null
}
```

### Internal Server Error (500)
```json
{
  "success": false,
  "message": "Failed to retrieve surveys: Internal server error",
  "statusCode": 500,
  "data": null
}
```

---

## Draft Mode Workflow

The API supports a draft mode workflow allowing users to save partial submissions:

### Step 1: Initial Save (Partial)
```bash
curl -X POST http://localhost:8080/api/v1/startup-surveys/draft \
  -H "Content-Type: application/json" \
  -d '{
    "startupName": "My Startup",
    "email": "founder@example.com"
  }'
```
Returns ID: 1

### Step 2: Update Draft with More Info
```bash
curl -X PATCH http://localhost:8080/api/v1/startup-surveys/1 \
  -H "Content-Type: application/json" \
  -d '{
    "founderName": "John Doe",
    "ageOfPrimaryFounder": 28
  }'
```

### Step 3: Final Submission
```bash
curl -X POST http://localhost:8080/api/v1/startup-surveys/submit \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "startupName": "My Startup",
    "email": "founder@example.com",
    "founderName": "John Doe"
  }'
```

---

## Multi-Select Enum Fields

The following fields accept multiple values as arrays:

- `businessMentoringTypes` - Multiple business mentoring types
- `supportNeeded` - Multiple support types needed
- `primaryChallenges` - Multiple primary challenges
- `supportRequired` - Multiple support required types
- `productDevelopmentSupports` - Multiple product development supports
- `digitalMaturityTools` - Multiple digital maturity tools
- `technicalInfrastructureSupports` - Multiple technical infrastructure supports
- `infrastructureAssistance` - Multiple infrastructure assistance types

**Example:**
```json
{
  "businessMentoringTypes": ["BUSINESS_MODEL_REFINEMENT", "PRICING_STRATEGY"],
  "supportRequired": ["BRANDING_MARKETING", "DIGITAL_PRESENCE"],
  "primaryChallenges": ["AWARENESS", "PRICING"]
}
```

---

## Key Features

✅ **Draft Mode Support** - Save incomplete surveys and update later
✅ **Partial Updates** - Use PATCH to update only specific fields
✅ **Comprehensive Filtering** - Search by email, startup name, or ID
✅ **Pagination & Sorting** - Efficient data retrieval
✅ **Multi-Select Fields** - Support for multiple enum values
✅ **Audit Trail** - Track creation and update timestamps and users
✅ **RESTful Design** - Follows REST conventions
✅ **Standardized Responses** - Consistent API response format

---

## HTTP Status Codes

| Code | Meaning |
|------|---------|
| 200 | OK - Request successful |
| 201 | Created - Resource created successfully |
| 400 | Bad Request - Invalid input |
| 404 | Not Found - Resource not found |
| 500 | Internal Server Error - Server error |

---

## Rate Limiting
Currently no rate limiting. Consider implementing in production.

## Authentication
Add security/authentication as per your project requirements.

## CORS
Configure CORS settings in application.properties as needed.

