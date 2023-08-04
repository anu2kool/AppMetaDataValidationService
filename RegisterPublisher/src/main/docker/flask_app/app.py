from flask import Flask, jsonify, request
from google_play_scraper import app as app2

app = Flask(__name__)

@app.route('/home', methods=['POST','GET'])
def process_json():
        data = request.args.get("app_bundle_id")
        #print(data)
        #print("hello")
        try:
                result = app2(data, lang='en')
                #print(result)
                return result
        except:
                return ""

if __name__=='__main__':
    app.run(host='0.0.0.0')
