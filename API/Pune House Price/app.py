from unittest import result
from flask import Flask, request, jsonify
import pickle
import numpy as np

model = pickle.load(open('pune4.pkl','rb'))

app = Flask(__name__)

@app.route('/')
def home():
    return "Pune House Price Prediction App"

@app.route('/predict',methods=['POST'])

#"Viman Nagar","Plot  Area",1000,2,3,1,"Ready To Move"
#"Kothrud","Plot  Area",1000,2,3,1,"Ready To Move"

def predict():
    #location,area,sqft,bath,bhk,balcony,ready

    Col_Name = ['bath', 'balcony', 'bhk', 'new_total_sqft', 'Alandi Road', 'Ambegaon Budruk', 'Anandnagar', 'Aundh', 'Aundh Road', 'Balaji Nagar', 'Baner', 'Baner road', 'Bhandarkar Road', 'Bhavani Peth', 'Bibvewadi', 'Bopodi', 'Budhwar Peth', 'Bund Garden Road', 'Camp', 'Chandan Nagar', 'Dapodi', 'Deccan Gymkhana', 'Dehu Road', 'Dhankawadi', 'Dhayari Phata', 'Dhole Patil Road', 'Erandwane', 'Fatima Nagar', 'Fergusson College Road', 'Ganesh Peth', 'Ganeshkhind', 'Ghorpade Peth', 'Ghorpadi', 'Gokhale Nagar', 'Gultekdi', 'Guruwar peth', 'Hadapsar', 'Hadapsar Industrial Estate', 'Hingne Khurd', 'Jangali Maharaj Road', 'Kalyani Nagar', 'Karve Nagar', 'Karve Road', 'Kasba Peth', 'Katraj', 'Khadaki', 'Khadki', 'Kharadi', 'Kondhwa', 'Kondhwa Budruk', 'Kondhwa Khurd', 'Koregaon Park', 'Kothrud', 'Law College Road', 'Laxmi Road', 'Lulla Nagar', 'Mahatma Gandhi Road', 'Mangalwar peth', 'Manik Bagh', 'Market yard', 'Model colony', 'Mukund Nagar', 'Mundhawa', 'Nagar Road', 'Nana Peth', 'Narayan Peth', 'Narayangaon', 'Navi Peth', 'Padmavati', 'Parvati Darshan', 'Pashan', 'Paud Road', 'Pirangut', 'Prabhat Road', 'Pune Railway Station', 'Rasta Peth', 'Raviwar Peth', 'Sadashiv Peth', 'Sahakar Nagar', 'Salunke Vihar', 'Sasson Road', 'Satara Road', 'Senapati Bapat Road', 'Shaniwar Peth', 'Shivaji Nagar', 'Shukrawar Peth', 'Sinhagad Road', 'Somwar Peth', 'Swargate', 'Tilak Road', 'Uruli Devachi', 'Vadgaon Budruk', 'Viman Nagar', 'Vishrant Wadi', 'Wadgaon Sheri', 'Wagholi', 'Wakadewadi', 'Wanowrie', 'Warje', 'Yerawada', 'Ready To Move', 'Built-up  Area', 'Carpet  Area', 'Plot  Area']
 
    Col = np.array(Col_Name)
    location = request.form.get('location')
    area = request.form.get('area')
    sqft = request.form.get('sqft')
    bath = request.form.get('bath')
    bhk = request.form.get('bhk')
    balcony = request.form.get('balcony')
    ready = request.form.get('ready')

    #loc_index=np.where(X.columns==location)[0][0]
    #area_index=np.where(X.columns==area)[0][0]
    #ready_index=np.where(X.columns==ready)[0][0]
    
    x=np.zeros(104)
    #print(x.shape)
    x[0]=bath
    x[1]=balcony
    x[2]=bhk
    x[3]=sqft
    if location in Col_Name:
        x[Col_Name.index(location)]=1
    if area in Col_Name:
        x[Col_Name.index(area)]=1
    if ready in Col_Name:
        x[Col_Name.index(ready)]=1

    input_query = np.array([x])
    #x.reshape(1,-1)
    # y = []
    # y.append(x)
    # y.append(x)
    #result = model.predict(y)[0]
    result = model.predict(input_query)[0]
    #result = {'locatin':location,'area':area,'sqft':sqft,'bath':bath,'bhk':bhk,'balcony':balcony,'ready':ready}

    return jsonify({'price': str("{:.2f}".format(float(result))+" Lakhs")})
    #return jsonify(result)

    

if __name__=='__main__':
    app.run(debug=True)
